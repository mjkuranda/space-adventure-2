package com.github.mjkuranda.spaceadventure2.entities;

import com.github.mjkuranda.spaceadventure2.entities.shootable.CantShoot;

import java.util.LinkedList;

public class Asteroid extends SpaceEntity {

    public Asteroid(LinkedList<Entity> subscriber, float x, float y) {
        super(EntityType.ASTEROID, subscriber, EntityTurn.INCOMING, new CantShoot(), EntityShapes.ASTEROID_SHAPE, x, y, 0.05f, 100);
    }

    public Asteroid(float x, float y) {
        super(EntityType.ASTEROID, null, EntityTurn.INCOMING, new CantShoot(), EntityShapes.ASTEROID_SHAPE, x, y, 0.05f, 100);
    }

    public Asteroid() {
        // FIXME: speed up
        super(EntityType.ASTEROID, null, EntityTurn.INCOMING, new CantShoot(), EntityShapes.ASTEROID_SHAPE, -1, -1, 0.01f, 100);
    }
}
