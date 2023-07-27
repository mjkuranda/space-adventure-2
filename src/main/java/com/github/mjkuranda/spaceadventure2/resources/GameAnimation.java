package com.github.mjkuranda.spaceadventure2.resources;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class GameAnimation implements GameResource<Animation> {

    public static Animation EXPLOSION;

    private Animation animation;


    public GameAnimation(String spriteSheetPath, int tw, int th, int duration) throws SlickException {
        Image image = new Image(getResourcePath("animations", spriteSheetPath));
        animation = new Animation(new SpriteSheet(image, tw, th), duration);
    }

    public GameAnimation(String spriteSheetPath) throws SlickException {
        this(spriteSheetPath, 100, 100, 30);
    }

    public GameAnimation(String spriteSheetPath, int duration) throws SlickException {
        this(spriteSheetPath, 100, 100, duration);
    }

    public static void load() throws SlickException {
        EXPLOSION = new GameAnimation("explosion.png").get();
    }

    @Override
    public Animation get() {
        return animation;
    }
}
