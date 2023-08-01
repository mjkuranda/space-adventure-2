package com.github.mjkuranda.spaceadventure2.entities;

import com.github.mjkuranda.spaceadventure2.entities.shootable.Shootable;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

import java.util.Deque;
import java.util.LinkedList;

public abstract class SpaceEntity extends Entity {

    /** Shootable strategy */
    private Shootable shootable;

    public SpaceEntity(EntityType type, LinkedList<Entity> subscriber, Deque<Entity> subscriberOfMapped, EntityTurn turn, Shootable shootable, Shape shape, Image image, float x, float y, float speed, int durability, int score) {
        super(type, subscriber, subscriberOfMapped, turn, shape, image, x, y, speed, durability, score);

        this.shootable = shootable;
    }

    public void tryToShoot() {
        shootable.shoot();
    }
}
