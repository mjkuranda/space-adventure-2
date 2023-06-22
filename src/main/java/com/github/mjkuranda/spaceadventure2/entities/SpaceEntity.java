package com.github.mjkuranda.spaceadventure2.entities;

import com.github.mjkuranda.spaceadventure2.entities.missiles.Missile;
import com.github.mjkuranda.spaceadventure2.entities.shootable.Shootable;

import java.util.LinkedList;

public abstract class SpaceEntity extends Entity {

    /** Shootable strategy */
    private Shootable shootable;

    public SpaceEntity(EntityType type, LinkedList<Entity> subscriber, EntityTurn turn, Shootable shootable, Missile missile, float x, float y, float speed, int durability) {
        super(type, subscriber, turn, x, y, speed, durability);

        this.shootable = shootable;
    }

    public void tryToShoot() {
        shootable.shoot();
    }
}
