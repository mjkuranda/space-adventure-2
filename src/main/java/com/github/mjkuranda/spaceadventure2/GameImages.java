package com.github.mjkuranda.spaceadventure2;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class GameImages {

    private static final String PREFIX_IMAGE_PATH = "src/main/resources/img/";

    private static GameImages instance;

    public static Image ASTEROID_IMAGE,
                        SPACESHIP_IMAGE;

    public static Image BACKGROUND_IMAGE;

    public static GameImages getInstance() {
        if (instance == null) {
            instance = new GameImages();
        }

        return instance;
    }

    public void init() throws SlickException {
        initEntityImages();
        initBackgrounds();
    }

    private void initEntityImages() throws SlickException {
        ASTEROID_IMAGE = load("entities/asteroid.png");
        SPACESHIP_IMAGE = load("entities/spaceship.png");
    }

    private void initBackgrounds() throws SlickException {
        BACKGROUND_IMAGE = load("backgrounds/background.png");
    }

    private Image load(String path) throws SlickException {
        return new Image(PREFIX_IMAGE_PATH + path);
    }
}
