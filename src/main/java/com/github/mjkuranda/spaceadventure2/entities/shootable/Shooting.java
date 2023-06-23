package com.github.mjkuranda.spaceadventure2.entities.shootable;

import com.github.mjkuranda.spaceadventure2.entities.Entity;
import com.github.mjkuranda.spaceadventure2.entities.missiles.Missile;
import com.github.mjkuranda.spaceadventure2.entities.missiles.MissileType;

import java.util.LinkedList;

public class Shooting implements Shootable {

    private LinkedList<Entity> subscriber;

    private MissileType missileType;

    public Shooting(LinkedList<Entity> subscriber, MissileType missileType) {
        this.subscriber = subscriber;
        this.missileType = missileType;
    }

    @Override
    public void shoot() {
        // FIXME
        subscriber.add(null);
    }
}
