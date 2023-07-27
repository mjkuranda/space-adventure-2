package com.github.mjkuranda.spaceadventure2.entities;

import org.newdawn.slick.Animation;
import org.newdawn.slick.geom.Shape;

public class Particle extends Entity {

    private Animation animation;

    public Particle(Animation animation, float x, float y, Shape shape) {
        super(EntityType.PARTICLE, null, EntityTurn.INCOMING, shape, null, x, y, 0.0f, 0, 0);

        this.animation = animation.copy();

        this.animation.setLooping(false);
        this.animation.start();
    }

    public boolean isDead() {
        return animation.isStopped();
    }

    public Animation getAnimation() {
        return animation;
    }
}
