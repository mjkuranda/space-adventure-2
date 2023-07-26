package com.github.mjkuranda.spaceadventure2.entities;

import com.github.mjkuranda.spaceadventure2.entities.missiles.MissileType;
import com.github.mjkuranda.spaceadventure2.entities.shootable.Shooting;
import com.github.mjkuranda.spaceadventure2.resources.GameImage;

import java.util.LinkedList;

public class Spaceship extends SpaceEntity {

    public Spaceship(LinkedList<Entity> missileSubscriber, float x, float y) {
        super(EntityType.SPACESHIP, null, EntityTurn.OUTCOMING, new Shooting(missileSubscriber, MissileType.LASER), EntityShapes.SPACESHIP_SHAPE, GameImage.SPACESHIP.get(), x, y, 0.075f, 100, 0);
    }
}
