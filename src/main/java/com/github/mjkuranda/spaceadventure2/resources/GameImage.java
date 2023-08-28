package com.github.mjkuranda.spaceadventure2.resources;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class GameImage implements GameResource<Image> {

    public static Image ASTEROID;
    public static Image SPACESHIP;

    public static Image BACKGROUND;
    public static Image BACKGROUND_1;
    public static Image BACKGROUND_2;
    public static Image BACKGROUND_3;
    public static Image BACKGROUND_4;

    public static Image LOGO;

    private Image img;

    public GameImage(String path) throws SlickException {
        img = new Image(getResourcePath("img", path));
    }

    public static void load() throws SlickException {
        initEntityImages();
        initBackgrounds();
        initOthers();
    }

    private static void initEntityImages() throws SlickException {
        ASTEROID = new GameImage("entities/asteroid.png").get(); // https://www.space.com/asteroid-mining-bring-space-rocks-to-earth
        SPACESHIP = new GameImage("entities/spaceship.png").get(); // https://www.shutterstock.com/pl/search/spaceship-back
    }

    private static void initBackgrounds() throws SlickException {
        BACKGROUND = new GameImage("backgrounds/background.png").get();
        BACKGROUND_1 = new GameImage("backgrounds/background-1.jpg").get();
        BACKGROUND_2 = new GameImage("backgrounds/background-2.jpg").get();
        BACKGROUND_3 = new GameImage("backgrounds/background-3.jpg").get();
        BACKGROUND_4 = new GameImage("backgrounds/background-4.jpg").get();
    }

    private static void initOthers() throws SlickException {
        LOGO = new GameImage("game-logo.png").get();
    }

    @Override
    public Image get() {
        return img;
    }
}
