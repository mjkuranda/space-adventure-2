package com.github.mjkuranda.spaceadventure2.entities.missiles;

import com.github.mjkuranda.spaceadventure2.entities.Entity;
import com.github.mjkuranda.spaceadventure2.entities.EntityTurn;
import com.github.mjkuranda.spaceadventure2.entities.EntityType;

import java.util.LinkedList;

public class LaserMissile extends Missile {

    public LaserMissile(LinkedList<Entity> subscriber, EntityTurn turn, float x, float y) {
        super(EntityType.LASER_MISSILE, subscriber, turn, x, y, 10, 100, 50);
    }
}
