package com.github.mjkuranda.spaceadventure2.resources;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class GameImage implements GameResource<Image> {

    public static GameImage ASTEROID;
    public static GameImage SPACESHIP;

    public static GameImage BACKGROUND;

    private Image img;

    public GameImage(String path) throws SlickException {
        img = new Image(getResourcePath("img", path));
    }

    public static void load() throws SlickException {
        initEntityImages();
        initBackgrounds();
    }

    @Override
    public Image get() {
        return img;
    }

    private static void initEntityImages() throws SlickException {
        ASTEROID = new GameImage("entities/asteroid.png"); // https://www.space.com/asteroid-mining-bring-space-rocks-to-earth
        SPACESHIP = new GameImage("entities/spaceship.png"); // https://www.shutterstock.com/pl/search/spaceship-back
    }

    private static void initBackgrounds() throws SlickException {
        BACKGROUND = new GameImage("backgrounds/background.png");
    }
}
