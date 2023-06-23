package com.github.mjkuranda.spaceadventure2.map;

import com.github.mjkuranda.spaceadventure2.entities.*;
import com.github.mjkuranda.spaceadventure2.entities.missiles.Missile;

import java.util.LinkedList;
import java.util.Random;

public class GameMap {

    public static final int X_SIZE = 16;
    public static final int Y_SIZE = 16;

    /** Player spaceship */
    private SpaceShip player;

    /** All lines of map */
    private LinkedList<Entity>[] entityLines;
    private LinkedList<Missile> missileLines;

    public GameMap() {
        initLists();

        player = new SpaceShip(missileLines, X_SIZE / 2.0f, Y_SIZE - 1);
    }

    public void spawn(EntityType type) {
        float x = new Random().nextFloat() * X_SIZE;
        float y = 0;

        int xInt = (int) x;

        Entity entity = getEntity(type);
        entity.setCoords(x, y);
        entity.setSubscriber(entityLines[xInt]);

        entityLines[xInt].add(entity);
    }

    public void update() {
        /** Update entities */
        for (var line : entityLines) {
            var it = line.iterator();

            while (it.hasNext()) {
                var entity = it.next();

                entity.move();

                if (isOutOfMap(entity)) {
                    it.remove();
                    entity.destroy();
                }
            }
        }
    }

    public SpaceShip getPlayer() {
        return player;
    }

    public LinkedList<Entity>[] getSpaceEntityList() {
        return entityLines;
    }

    private Entity getEntity(EntityType type) {
        return switch(type) {
            case ASTEROID -> new Asteroid();
            case LASER_MISSILE -> null;
            case NONE -> null;
            default -> null;
        };
    }

    private boolean isOutOfMap(Entity e) {
        return e.getY() * 32 > Y_SIZE * 32;
    }

    private void initLists() {
        entityLines = new LinkedList[Y_SIZE];
        missileLines = new LinkedList<>();

        initList(entityLines);
    }

    private void initList(LinkedList<Entity>[] listLines) {
        for (int i = 0; i < listLines.length; i++) {
            listLines[i] = new LinkedList<>();
        }
    }
}
