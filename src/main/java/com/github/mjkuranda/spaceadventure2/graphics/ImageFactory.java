package com.github.mjkuranda.spaceadventure2.graphics;

import com.github.mjkuranda.spaceadventure2.GameImages;
import com.github.mjkuranda.spaceadventure2.entities.EntityType;
import org.newdawn.slick.Image;

public class ImageFactory {

    public static Image get(EntityType type) {
        return switch(type) {
            case ASTEROID -> GameImages.ASTEROID_IMAGE;
            case SPACESHIP -> GameImages.SPACESHIP_IMAGE;
            default -> null;
        };
    }
}
