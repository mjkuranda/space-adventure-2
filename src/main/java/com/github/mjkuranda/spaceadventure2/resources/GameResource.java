package com.github.mjkuranda.spaceadventure2.resources;

public interface GameResource<T> {
    default String getResourcePath(String type, String path) {
        return PREFIX_PATH + type + "/" + path;
    }

    T get();

    String PREFIX_PATH = "src/main/resources/";
}
