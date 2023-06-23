package com.github.mjkuranda.spaceadventure2.entities;

import com.github.mjkuranda.spaceadventure2.entities.missiles.MissileType;
import com.github.mjkuranda.spaceadventure2.entities.shootable.Shooting;

import java.util.LinkedList;

public class SpaceShip extends SpaceEntity {

    public SpaceShip(LinkedList<Entity> missileSubscriber, float x, float y) {
        super(EntityType.SPACESHIP, null, EntityTurn.OUTCOMING, new Shooting(missileSubscriber, MissileType.LASER), x, y, 0.2f, 100);
    }
}
