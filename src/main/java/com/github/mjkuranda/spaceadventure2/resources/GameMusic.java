package com.github.mjkuranda.spaceadventure2.resources;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

public class GameMusic implements GameResource<Music> {

    public static Music BACKGROUND_MUSIC;

    private Music music;

    public GameMusic(String path) throws SlickException {
        music = new Music(getResourcePath("music", path));
    }

    public static void load() throws SlickException {
        BACKGROUND_MUSIC = new GameMusic("music.ogg").get();
    }

    @Override
    public Music get() {
        return music;
    }
}
