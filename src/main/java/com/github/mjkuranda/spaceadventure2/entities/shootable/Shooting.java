package com.github.mjkuranda.spaceadventure2.entities.shootable;

import com.github.mjkuranda.spaceadventure2.entities.missiles.Missile;

import java.util.LinkedList;

public class Shooting implements Shootable {

    private LinkedList<Missile> subscriber;

    private Missile missile;

    public Shooting(LinkedList<Missile> subscriber, Missile missile) {
        this.subscriber = subscriber;
        this.missile = missile;
    }

    @Override
    public void shoot() {
        subscriber.add(missile);
    }
}
