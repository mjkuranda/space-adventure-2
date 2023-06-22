package com.github.mjkuranda.spaceadventure2.entities;

import com.github.mjkuranda.spaceadventure2.entities.shootable.CantShoot;

import java.util.LinkedList;

public class Asteroid extends SpaceEntity {

    public Asteroid(LinkedList<Entity> subscriber, float x, float y) {
        super(EntityType.ASTEROID, subscriber, EntityTurn.INCOMING, new CantShoot(), null, x, y, 2, 100);
    }
}
