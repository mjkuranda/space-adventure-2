package com.github.mjkuranda.spaceadventure2;

import com.github.mjkuranda.spaceadventure2.entities.*;
import com.github.mjkuranda.spaceadventure2.entities.missiles.LaserMissile;
import com.github.mjkuranda.spaceadventure2.entities.missiles.Missile;
import com.github.mjkuranda.spaceadventure2.entities.missiles.MissileType;
import com.github.mjkuranda.spaceadventure2.entities.Particle;
import com.github.mjkuranda.spaceadventure2.resources.GameAnimation;
import com.github.mjkuranda.spaceadventure2.resources.GameSound;
import com.github.mjkuranda.spaceadventure2.states.StatesId;
import com.github.mjkuranda.spaceadventure2.states.highscore.HighScoreHandler;
import com.github.mjkuranda.spaceadventure2.states.highscore.HighScoreRecord;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import java.time.LocalDate;
import java.util.*;

public class GameData {

    public static final int X_SIZE = 16;
    public static final int Y_SIZE = 16;

    public static final int MAX_ENTITY_LIST = 32;

    public static final int GAME_TIME_LENGTH = 1000 * 60 * 5; // 5 minutes

    /** Start game time */
    private static long startTime;

    /** Player spaceship */
    private static Spaceship player;

    /** All lines of map */
    private LinkedList<Entity>[] entityLines;

    /** Mapped entities */
    private Deque<Entity> entities;

    /** All missiles shoot by player */
    private static LinkedList<Entity> playerMissiles;

    /** All particles in map */
    private LinkedList<Particle> particles;

    public GameData() {
        initLists();

        player = new Spaceship(playerMissiles, X_SIZE / 2.0f, Y_SIZE - 2);
    }

    /***
     * Update function per tick
     * @param container GameContainer
     * @param game StateBasedGame
     */
    public void update(GameContainer container, StateBasedGame game) {
        /** Check game time */
        if (hasGameEnded()) {
            gameOver(game);
        }

        /** Update entities */
        for (var line : entityLines) {
            var it = line.iterator();

            while (it.hasNext()) {
                var entity = it.next();
                entity.move();

                if (isOutOfMap(entity)) {
                    remove(entity, it);
                }
            }
        }

        /** Update player missiles */
        var missileIt = playerMissiles.iterator();

        while (missileIt.hasNext()) {
            Missile missile = (Missile) missileIt.next();
            int x = (int) missile.getX();
            missile.move();

            Entity e =  null;                   float d  = Float.MAX_VALUE;
            Entity e1 = getFirst(x - 1);     float d1 = missile.getDistanceTo(e1);
            Entity e2 = getFirst(x);            float d2 = missile.getDistanceTo(e2);
            Entity e3 = getFirst(x + 1);     float d3 = missile.getDistanceTo(e3);

            if (missile.collides(e1)) {
                e = e1;
                d = d1;
            }

            if (missile.collides(e2) && d2 < d) {
                e = e2;
                d = d2;
            }

            if (missile.collides(e3) && d3 < d) {
                e = e3;
                d = d3;
            }

            if (e != null) {
                e.damage(missile.getDamage());
                destroy(missile, missileIt);
                particles.add(new Particle(GameAnimation.EXPLOSION, missile));

                if (!e.isAlive()) {
                    Random r = new Random();
                    (r.nextBoolean() ? GameSound.EXPLOSION_ASTEROID : GameSound.EXPLOSION_ASTEROID_2).play();
                    particles.add(new Particle(GameAnimation.EXPLOSION, e));
                    PlayerData.getInstance().addMissiles(r.nextInt(3) + 2);
                }

                continue;
            }

            if (isOutOfMap(missile)) {
                remove(missile, missileIt);
            }
        }

        /** Particle update */
        var particleIt = particles.iterator();

        while (particleIt.hasNext()) {
            Particle particle = particleIt.next();

            if (particle.isDead()) {
                particleIt.remove();
            }
        }

        /** Player update */
        PlayerData.getInstance().addDistance(player.getSpeed());

        Input in = container.getInput();

        if (in.isKeyDown(Input.KEY_A)) {
            player.move(EntityDirection.LEFT);
        }

        if (in.isKeyDown(Input.KEY_D)) {
            player.move(EntityDirection.RIGHT);
        }

        if (in.isKeyPressed(Input.KEY_G)) {
            spawn(EntityType.ASTEROID);
        }

        PlayerData playerData = PlayerData.getInstance();

        if (in.isKeyPressed(Input.KEY_SPACE) && playerData.hasMissile()) {
            spawn(MissileType.LASER);
            playerData.takeMissile();
            GameSound.SHOOT.play(1.0f, 0.5f);
        }

        /** Player collision */
        Entity e = playerCollides();
        PlayerData.getInstance().unvibrate();

        if (e != null) {
            player.damage(e.getDurability());
            spawn(new Particle(GameAnimation.EXPLOSION, e));
            e.remove();
            PlayerData.getInstance().vibrate();
            GameSound.EXPLOSION.play(1.0f, 0.5f);

            if (!player.isAlive()) {
                gameOver(game);
            }
        }

        /** Spawner update */
        SpawnManager.getInstance().update();

        /** Spawn new entities */
        float prob = new Random().nextFloat();

        if (prob < getProbabilityToSpawnEntity()) {
            spawn(EntityType.ASTEROID);
        }
    }

    /**
     * Resets game data
     */
    public void reset() {
        /** Resets player */
        player.setCoords(X_SIZE / 2.0f, Y_SIZE - 2);

        /** Resets lists */
        for (var line : entityLines) {
            line.clear();
        }
        entities.clear();
        playerMissiles.clear();

        /** Resets particles */
        particles.clear();
    }

    /**
     * Spawns a new space entity
     * @param type EntityType
     */
    public void spawn(EntityType type) {
        if (entities.size() >= MAX_ENTITY_LIST) {
            System.err.println("GameData/spawn\tError:\tmaximal count of entities is exceeded!");

            return;
        }

        SpawnManager spawner = SpawnManager.getInstance();

        if (!spawner.canSpawnNewEntity()) {
            System.err.println("GameData/spawn\tError:\tno empty lines to put a new entity!");

            return;
        }

        Vector2f coordinates = spawner.getCoordinates();
        int x = (int) coordinates.getX();
        int y = (int) coordinates.getY();

        float xOffset = (float) (Math.random() * 0.5f);

        if (Math.random() > 0.5f) {
            xOffset *= -1;
        }

        if (x + xOffset < 0 || x + xOffset > X_SIZE - 1) {
            xOffset = 0;
        }

        Entity entity = getEntity(type)
                .setCoords(x + xOffset, y)
                .setSubscriber(entityLines[x])
                .setSubscriberOfMapped(entities);

        entityLines[x].add(entity);
        entities.addFirst(entity);
    }

    public void gameOver(StateBasedGame game) {
        PlayerData stats = PlayerData.getInstance();
        HighScoreHandler.getInstance().inputRecord(new HighScoreRecord(stats.getName(), stats.getScore(), LocalDate.now().toString()));

        game.enterState(StatesId.GAME_OVER_MENU);
        game.getContainer().getInput().clearKeyPressedRecord();
        GameSound.GAME_OVER.play();
        reset();

        try {
            game.getState(StatesId.GAME_OVER_MENU).init(game.getContainer(), game);
        } catch (SlickException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns probability to spawn a new entity
     * @return 1%-10% probability to spawn a new entity
     */
    private float getProbabilityToSpawnEntity() {
        float ratio = (float) (System.currentTimeMillis() - startTime) / (float) GAME_TIME_LENGTH;

        return ratio * 0.09f + 0.01f;
    }

    /***
     * Sets a new game start time
     */
    public static void startNewGame() {
        startTime = System.currentTimeMillis();
        PlayerData.getInstance().reset();
        player = new Spaceship(playerMissiles, X_SIZE / 2.0f, Y_SIZE - 2);
    }

    /***
     * Returns remaining game time
     * @return remaining time to the finish of game
     */
    public static long getRemainingTime() {
        return (startTime + GAME_TIME_LENGTH) - System.currentTimeMillis();
    }

    /***
     * Returns if game has ended or not
     * @return if game has ended or not
     */
    private static boolean hasGameEnded() {
        return getRemainingTime() < 0;
    }

    /***
     * Spawns a new missile
     * @param type MissileType
     */
    public void spawn(MissileType type) {
        float x = player.getX() - EntityShapes.MISSILE_SHAPE.getX();
        float y = player.getY() - 1;

        Missile missile = (Missile) getMissile(type)
                .setCoords(x, y)
                .setSubscriber(playerMissiles)
                .setTurn(EntityTurn.OUTCOMING);

        playerMissiles.add(missile);
    }

    /***
     * Removes an entity from game data
     * @param e Entity
     */
    public void destroy(Entity e) {
        e.destroy();
    }

    /***
     * Removes a player missile from game data
     * @param missile Missile
     */
    public void destroy(Missile missile) {
        missile.destroy();
    }

    /***
     * Spawns a new particle
     * @param particle Particle
     */
    public void spawn(Particle particle) {
        this.particles.add(particle);
    }

    /***
     * Returns a player spaceship
     * @return player Spaceship
     */
    public Spaceship getPlayer() {
        return player;
    }

    /***
     * Returns space entity list
     * @return arrays of entity list
     */
    public LinkedList<Entity>[] getSpaceEntityList() {
        return entityLines;
    }

    /***
     * Returns missile list
     * @return missile list
     */
    public LinkedList<Entity> getPlayerMissiles() {
        return playerMissiles;
    }

    /***
     * Returns particle list
     * @return particle list
     */
    public LinkedList<Particle> getParticles() {
        return particles;
    }

    /***
     * Returns mapped entities to proper rendering
     * @return entity list
     */
    public Deque<Entity> getMappedEntities() {
        return entities;
    }

    private void destroy(Missile missile, Iterator<Entity> it) {
        it.remove();
        missile.destroy();
    }

    private void remove(Entity e, Iterator<Entity> it) {
        it.remove();
        e.remove();
    }

    private void destroy(Entity e, Iterator<Entity> it) {
        it.remove();
        e.destroy();
    }

    private Entity getEntity(EntityType type) {
        return switch(type) {
            case ASTEROID -> new Asteroid();
            case LASER_MISSILE -> null;
            case NONE -> null;
            default -> null;
        };
    }

    private Missile getMissile(MissileType type) {
        return switch (type) {
            case LASER -> new LaserMissile();
            default -> null;
        };
    }

    private boolean isOutOfMap(Entity e) {
        return e.getY() > Y_SIZE || e.getY() < 0;
    }

    private void initLists() {
        entityLines = new LinkedList[Y_SIZE];
        entities = new ArrayDeque<>();
        playerMissiles = new LinkedList<>();
        particles = new LinkedList<>();

        initList(entityLines);
    }

    private void initList(LinkedList<Entity>[] listLines) {
        for (int i = 0; i < listLines.length; i++) {
            listLines[i] = new LinkedList<>();
        }
    }

    private Entity getFirst(int x) {
        if (x < 0 || x > GameData.X_SIZE - 1) {
            return null;
        }

        if (entityLines[x].size() == 0) {
            return null;
        }

        return entityLines[x].getFirst();
    }

    private Entity playerCollides() {
        float x = player.getX();

        Entity e1 = playerCollides(x);
        Entity e2 = playerCollides(x - 1);
        Entity e3 = playerCollides(x + 1);

        if (e1 != null) return e1;
        if (e2 != null) return e2;
        if (e3 != null) return e3;

        return null;
    }

    private Entity playerCollides(float px) {
        if (px < 0 || px > GameData.X_SIZE - 1) {
            return null;
        }

        int x = (int) px;

        if (entityLines[x].size() == 0) {
            return null;
        }

        Entity entity = entityLines[x].getFirst();

        return player.collides(entity) ? entity : null;
    }
}

class SpawnManager {

    private static SpawnManager instance;

    /** Empty lines */
    private List<Integer> emptyLines;

    /** Recent lines */
    private Queue<SpawnZone> recentLines;

    /** Last time when was removed the oldest record with lines */
    private long spawnUpdate;

    public SpawnManager() {
        emptyLines = new LinkedList<>();
        recentLines = new LinkedList<>();
        spawnUpdate = System.currentTimeMillis();

        for (int i = 0; i < GameData.X_SIZE; i++) {
            emptyLines.add(i);
        }
    }

    public static SpawnManager getInstance() {
        if (instance == null) {
            instance = new SpawnManager();
        }

        return instance;
    }

    /**
     * Gives the coordinates of a new entity spawned on the map
     * @return Vector2D with coordinates
     */
    public Vector2f getCoordinates() {
        Random r = new Random();

        int lineId = r.nextInt(emptyLines.size());
        int line = emptyLines.get(lineId);
        recentLines.add(new SpawnZone(line));
        emptyLines.removeAll(Arrays.asList(line - 1, line, line + 1));

        return new Vector2f(line, 0);
    }

    /**
     * Returns flag if manager can spawn a new entity.
     * It can have a problem with empty lines...
     * @return flag, if it can spawn or not
     */
    public boolean canSpawnNewEntity() {
        return emptyLines.size() > 0;
    }

    public void update() {
        if (System.currentTimeMillis() - spawnUpdate >= 1000) {
            SpawnZone zone = recentLines.poll();

            if (zone != null) {
                emptyLines.add(zone.getLines()[0]);
                emptyLines.add(zone.getLines()[1]);
                emptyLines.add(zone.getLines()[2]);
            }

            spawnUpdate = System.currentTimeMillis();
        }
    }

    private class SpawnZone {
        int[] lines;

        public SpawnZone(int line) {
            lines = new int[3];

            if (line == 0) {
                lines[0] = line;
            } else {
                lines[0] = line - 1;
            }

            if (line == GameData.X_SIZE - 1) {
                lines[2] = line;
            } else {
                lines[2] = line + 1;
            }

            lines[1] = line;
        }

        public int[] getLines() {
            return lines;
        }
    }
}