package com.github.mjkuranda.spaceadventure2.entities.missiles;

import com.github.mjkuranda.spaceadventure2.entities.Entity;
import com.github.mjkuranda.spaceadventure2.entities.EntityTurn;
import com.github.mjkuranda.spaceadventure2.entities.EntityType;

import java.util.LinkedList;

public abstract class Missile extends Entity {

    /** Missile damage */
    private int damage;

    public Missile(EntityType type, LinkedList<Entity> subscriber, EntityTurn turn, float x, float y, float speed, int damage, int durability) {
        super(type, subscriber, turn, x, y, speed, durability);

        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
}
