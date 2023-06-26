package com.github.mjkuranda.spaceadventure2.entities;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public interface EntityShapes {
    Shape SPACESHIP_SHAPE = new Rectangle(0, 0, 1.0f, 1.0f);
    Shape ASTEROID_SHAPE = new Rectangle(0, 0, 1.0f, 1.0f);
    Shape MISSILE_SHAPE = new Rectangle(0.25f, 0, 0.25f, 1.0f);
}
