package com.github.mjkuranda.spaceadventure2;

import com.github.mjkuranda.spaceadventure2.entities.*;
import com.github.mjkuranda.spaceadventure2.entities.missiles.LaserMissile;
import com.github.mjkuranda.spaceadventure2.entities.missiles.Missile;
import com.github.mjkuranda.spaceadventure2.entities.missiles.MissileType;
import com.github.mjkuranda.spaceadventure2.states.StatesId;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class GameData {

    public static final int X_SIZE = 16;
    public static final int Y_SIZE = 16;

    /** Player spaceship */
    private Spaceship player;

    /** All lines of map */
    private LinkedList<Entity>[] entityLines;
    private LinkedList<Entity> playerMissiles;

    public GameData() {
        initLists();

        player = new Spaceship(playerMissiles, X_SIZE / 2.0f, Y_SIZE - 1);
    }

    /***
     * Update function per tick
     * @param container GameContainer
     * @param game StateBasedGame
     */
    public void update(GameContainer container, StateBasedGame game) {
        /** Update entities */
        for (var line : entityLines) {
            var it = line.iterator();

            while (it.hasNext()) {
                var entity = it.next();
                entity.move();

                if (isOutOfMap(entity)) {
                    destroy(entity, it);
                }
            }
        }

        /** Update player missiles */
        var missileIt = playerMissiles.iterator();

        while (missileIt.hasNext()) {
            var missile = missileIt.next();
            int x = (int) missile.getX();
            missile.move();

            if (missile.collides(getLast(x))) {
                destroy(missile, missileIt);
                getLast(x).destroy();

                continue;
            }

            if (missile.collides(getLast(x - 1))) {
                destroy(missile, missileIt);
                getLast(x - 1).destroy();

                continue;
            }

            if (missile.collides(getLast(x + 1))) {
                destroy(missile, missileIt);
                getLast(x + 1).destroy();

                continue;
            }

            if (isOutOfMap(missile)) {
                destroy(missile, missileIt);
            }
        }

        /** Player update */
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

        if (in.isKeyPressed(Input.KEY_SPACE)) {
            spawn(MissileType.LASER);
        }

        if (playerCollides()) {
            game.enterState(StatesId.INTRO);
        }

        /** Spawn new entities */
        float prob = new Random().nextFloat();

        if (prob < 0.01) {
            spawn(EntityType.ASTEROID);
        }
    }

    /**
     * Spawns a new space entity
     * @param type EntityType
     */
    public void spawn(EntityType type) {
        float x = new Random().nextFloat() * X_SIZE;
        float y = 0;

        int xInt = (int) x;

        Entity entity = getEntity(type)
                .setCoords(x, y)
                .setSubscriber(entityLines[xInt]);

        entityLines[xInt].add(entity);
    }

    /***
     * Spawns a new missile
     * @param type MissileType
     */
    public void spawn(MissileType type) {
        Missile missile = (Missile) getMissile(type)
                .setCoords(player.getX(), player.getY() - 1)
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

    private void destroy(Missile missile, Iterator<Entity> it) {
        it.remove();
        missile.destroy();
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
        return e.getY() * 32 > Y_SIZE * 32 || e.getY() < 0;
    }

    private void initLists() {
        entityLines = new LinkedList[Y_SIZE];
        playerMissiles = new LinkedList<>();

        initList(entityLines);
    }

    private void initList(LinkedList<Entity>[] listLines) {
        for (int i = 0; i < listLines.length; i++) {
            listLines[i] = new LinkedList<>();
        }
    }

    private Entity getLast(int x) {
        if (x < 0 || x > GameData.X_SIZE - 1) {
            return null;
        }

        if (entityLines[x].size() == 0) {
            return null;
        }

        return entityLines[x].getLast();
    }

    private boolean playerCollides() {
        float x = player.getX();

        return playerCollides(x) || playerCollides(x - 1) || playerCollides(x + 1);
    }

    private boolean playerCollides(float px) {
        if (px < 0 || px > GameData.X_SIZE - 1) {
            return false;
        }

        int x = (int) px;

        if (entityLines[x].size() == 0) {
            return false;
        }

        Entity entity = entityLines[x].getLast();
        float entityX = entity.getX() * 32;
        float entityY = entity.getY() * 32;

        float diffX = Math.abs(player.getX() * 32 - entityX);
        float diffY = Math.abs(player.getY() * 32 - entityY);

        return diffX > 0 && diffX < 32 && diffY > 0 && diffY < 32;
    }
}
