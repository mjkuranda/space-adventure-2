package com.github.mjkuranda.spaceadventure2.entities;

import java.util.LinkedList;

public abstract class Entity implements Moveable, Destroyable, Damageable {

    /** Entity type */
    private EntityType type;

    /** Entity collection */
    private LinkedList<Entity> subscriber;

    /** Entity turn */
    private EntityTurn turn;

    /** Entity coordinates */
    private float x, y;

    /** Entity speed */
    private float speed;

    /** Entity durability */
    private int durability;

    public Entity(EntityType type, LinkedList<Entity> subscriber, EntityTurn turn, float x, float y, float speed, int durability) {
        this.type = type;
        this.subscriber = subscriber;
        this.turn = turn;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.durability = durability;
    }

    @Override
    public void move() {
        x += speed;
        y += speed;
    }

    @Override
    public void destroy() {
        subscriber.remove(this);
    }

    @Override
    public void damage(int count) {
        durability -= count;

        if (durability <= 0) {
            destroy();
        }
    }

    public LinkedList<Entity> getSubscriber() {
        return subscriber;
    }

    public EntityType getType() {
        return type;
    }
}
