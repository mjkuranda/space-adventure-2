package com.github.mjkuranda.spaceadventure2.entities;

import com.github.mjkuranda.spaceadventure2.entities.shootable.CantShoot;

import java.util.LinkedList;

public class Asteroid extends SpaceEntity {

    public Asteroid(LinkedList<Entity> subscriber, float x, float y) {
        super(EntityType.ASTEROID, subscriber, EntityTurn.INCOMING, new CantShoot(), EntityShapes.ASTEROID_SHAPE, x, y, 0.02f, 100, 1);
    }

    public Asteroid(float x, float y) {
        this(null, x, y);
    }

    public Asteroid() {
        this(0.0f, 0.0f);
    }
}
