package com.github.mjkuranda.spaceadventure2.entities;

import com.github.mjkuranda.spaceadventure2.entities.shootable.CantShoot;
import com.github.mjkuranda.spaceadventure2.resources.GameImage;

import java.util.Deque;
import java.util.LinkedList;

public class Asteroid extends SpaceEntity {

    public Asteroid(LinkedList<Entity> subscriber, Deque<Entity> subscriberOfMapped, float x, float y) {
        super(EntityType.ASTEROID, subscriber, subscriberOfMapped, EntityTurn.INCOMING, new CantShoot(), EntityShapes.ASTEROID_SHAPE, GameImage.ASTEROID, x, y, 0.02f, 100, 1);
    }

    public Asteroid(float x, float y) {
        this(null, null, x, y);
    }

    public Asteroid() {
        this(0.0f, 0.0f);
    }
}
