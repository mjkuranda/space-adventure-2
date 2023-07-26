package com.github.mjkuranda.spaceadventure2.resources;

import org.newdawn.slick.SlickException;

public interface GameResource<T> {
    static void load() throws SlickException {}

    T get();

    String PREFIX_PATH = "src/main/resources/";
}
