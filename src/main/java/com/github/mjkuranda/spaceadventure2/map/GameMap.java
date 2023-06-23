package com.github.mjkuranda.spaceadventure2.map;

import com.github.mjkuranda.spaceadventure2.entities.Asteroid;
import com.github.mjkuranda.spaceadventure2.entities.Entity;
import com.github.mjkuranda.spaceadventure2.entities.SpaceEntity;

import java.util.LinkedList;
import java.util.Random;

public class GameMap {

    public static final int X_SIZE = 16;
    public static final int Y_SIZE = 16;

    /** All lines of map */
    private LinkedList<Entity>[] entityLines;

    public GameMap() {
        initLists();
    }

    public void spawn(SpaceEntity entity) {
        float x = new Random().nextFloat() * X_SIZE;
        float y = 0;

        int xInt = (int) x;

        entityLines[xInt].add(new Asteroid(entityLines[xInt], x, y));
    }

    private void initLists() {
        entityLines = new LinkedList[Y_SIZE];
        initList(entityLines);
    }

    private void initList(LinkedList<Entity>[] listLines) {
        for (int i = 0; i < listLines.length; i++) {
            listLines[i] = new LinkedList<>();
        }
    }
}
