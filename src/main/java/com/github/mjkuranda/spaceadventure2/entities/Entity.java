package com.github.mjkuranda.spaceadventure2.entities;

import com.github.mjkuranda.spaceadventure2.GameData;
import com.github.mjkuranda.spaceadventure2.PlayerStatistics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

import java.util.Deque;
import java.util.LinkedList;
import static java.lang.Math.sqrt;
import static java.lang.Math.pow;

public abstract class Entity implements Moveable, Destroyable, Damageable {

    /** Entity type */
    private EntityType type;

    /** Entity collection */
    private LinkedList<Entity> subscriber;

    /** Entity mapped collection */
    private Deque<Entity> subscriberOfMapped;

    /** Entity turn */
    private EntityTurn turn;

    /** Entity shape */
    private Shape shape;

    /** Entity coordinates */
    private float x, y;

    /** Entity speed */
    private float speed;

    /** Entity durability */
    private int durability;

    /** Entity score */
    private int score;

    /** Entity image */
    private Image image;

    public Entity(EntityType type, LinkedList<Entity> subscriber, Deque<Entity> subscriberOfMapped, EntityTurn turn, Shape shape, Image image, float x, float y, float speed, int durability, int score) {
        this.type = type;
        this.subscriber = subscriber;
        this.subscriberOfMapped = subscriberOfMapped;
        this.turn = turn;
        this.shape = shape;
        this.image = image;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.durability = durability;
        this.score = score;
    }

    @Override
    public void move() {
        if (turn == EntityTurn.INCOMING) {
            y += speed;
        }

        if (turn == EntityTurn.OUTCOMING) {
            y -= speed;
        }
    }

    public void move(EntityDirection direction) {
        if (direction == EntityDirection.LEFT) {
            x -= speed;
        }

        if (direction == EntityDirection.RIGHT) {
            x += speed;
        }

        if (x < 0) {
            x = 0;
        }

        if (x > GameData.X_SIZE - 1) {
            x = GameData.X_SIZE - 1;
        }
    }

    @Override
    public void destroy() {
        remove();
        PlayerStatistics.getInstance().addScore(score);
    }

    public void remove() {
        if (subscriber == null) {
            return;
        }

        subscriber.remove(this);

        if (subscriberOfMapped == null) {
            return;
        }

        subscriberOfMapped.remove(this);
    }

    @Override
    public void damage(int count) {
        durability -= count;

        if (!isAlive()) {
            destroy();
        }
    }

    /***
     * Returns a distance between two entities
     * @param e Entity
     * @return float difference
     */
    public float getDistanceTo(Entity e) {
        if (e == null) {
            return Float.POSITIVE_INFINITY;
        }

        return (float) sqrt(pow(x - e.getX(), 2) + pow(y - e.getY(), 2));
    }

    public boolean collides(Entity e) {
        if (e == null) {
            return false;
        }

        Shape shape1 = shape.prune();
        Shape shape2 = e.shape.prune();

        shape1.setLocation(x + shape.getX(), y + shape.getY());
        shape2.setLocation(e.getX() + e.shape.getX(), e.getY() + e.shape.getY());

        return shape1.intersects(shape2);
    }

    public Entity setCoords(float x, float y) {
        this.x = x;
        this.y = y;

        return this;
    }

    public int getDurability() {
        return durability;
    }

    public boolean isAlive() {
        return durability > 0;
    }

    public float getWidth() {
        return shape.getWidth();
    }

    public float getHeight() {
        return shape.getHeight();
    }

    public float getX() {
        return x + shape.getX();
    }

    public float getY() {
        return y + shape.getY();
    }

    public Shape getShape() {
        return shape;
    }

    public Image getImage() {
        return image;
    }

    public float getSpeed() {
        return speed;
    }

    public Entity setSubscriber(LinkedList<Entity> subscriber) {
        this.subscriber = subscriber;

        return this;
    }

    public Entity setSubscriberOfMapped(Deque<Entity> subscriberOfMapped) {
        this.subscriberOfMapped = subscriberOfMapped;

        return this;
    }

    public Entity setTurn(EntityTurn turn) {
        this.turn = turn;

        return this;
    }

    public LinkedList<Entity> getSubscriber() {
        return subscriber;
    }

    public EntityType getType() {
        return type;
    }
}
