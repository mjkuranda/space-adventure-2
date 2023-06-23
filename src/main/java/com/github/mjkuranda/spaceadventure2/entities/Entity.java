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
        if (turn == EntityTurn.INCOMING) {
            y += speed;

            return;
        }

        if (turn == EntityTurn.OUTCOMING) {
            y -= speed;
        }
    }

    public void move(EntityDirection direction) {
        if (direction == EntityDirection.LEFT) {
            x -= speed;

            return;
        }

        if (direction == EntityDirection.RIGHT) {
            x += speed;
        }
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

    public boolean collides(Entity e) {
        if (e == null) {
            return false;
        }

        float diffX = Math.abs(this.getX() - e.getX());
        float diffY = Math.abs(this.getY() - e.getY());

        return diffX > 0 && diffX < 1 && diffY > 0 && diffY < 1;
    }

    public void setCoords(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setSubscriber(LinkedList<Entity> subscriber) {
        this.subscriber = subscriber;
    }

    public void setTurn(EntityTurn turn) {
        this.turn = turn;
    }

    public LinkedList<Entity> getSubscriber() {
        return subscriber;
    }

    public EntityType getType() {
        return type;
    }
}
