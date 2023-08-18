package com.github.mjkuranda.spaceadventure2.resources;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import java.util.Random;

public class GameSound implements GameResource<Sound> {

    public static Sound BEEP;
    public static Sound CONFIRM;
    public static Sound EXPLOSION;
    public static Sound EXPLOSION_ASTEROID;
    public static Sound EXPLOSION_ASTEROID_2;
    public static Sound GAME_OVER;
    public static Sound KEY_2;
    public static Sound KEY_3;
    public static Sound KEY_4;
    public static Sound KEY_BACKSPACE;
    public static Sound KEY_SPACE_BAR;
    public static Sound KEY_W;
    public static Sound SHOOT;

    private Sound sound;

    public GameSound(String path) throws SlickException {
        sound = new Sound(getResourcePath("sounds", path));

        System.out.println("Sound " + path + " has been loaded!");
    }

    public static void load() throws SlickException {
        BEEP = new GameSound("beep.ogg").get();
        CONFIRM = new GameSound("confirm.ogg").get();
        EXPLOSION = new GameSound("explosion.ogg").get();
        EXPLOSION_ASTEROID = new GameSound("explosion-asteroid.ogg").get();
        EXPLOSION_ASTEROID_2 = new GameSound("explosion-asteroid-2.ogg").get();
        GAME_OVER = new GameSound("game-over.ogg").get();
        KEY_2 = new GameSound("key2.ogg").get();
        KEY_3 = new GameSound("key3.ogg").get();
        KEY_4 = new GameSound("key4.ogg").get();
        KEY_BACKSPACE = new GameSound("key-backspace.ogg").get();
        KEY_SPACE_BAR = new GameSound("key-spacebar.ogg").get();
        KEY_W = new GameSound("keyw.ogg").get();
        SHOOT = new GameSound("shoot.ogg").get();
    }

    public static void playKeySound() {
        Sound s = switch (new Random().nextInt(4)) {
            case 0 -> GameSound.KEY_2;
            case 1 -> GameSound.KEY_3;
            case 2 -> GameSound.KEY_4;
            case 3 -> GameSound.KEY_W;
            default -> throw new IllegalStateException("Unexpected value: " + new Random().nextInt(4));
        };

        s.play(1.0f, 2.0f);
    }

    @Override
    public Sound get() {
        return sound;
    }
}
