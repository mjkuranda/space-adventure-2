package com.github.mjkuranda.spaceadventure2.states.menus.options.values;

public abstract class MenuOptionValue<T> {

    private T value;

    public MenuOptionValue(T value) {
        this.value = value;
    }

    public void update(T newValue) {
        value = newValue;
    }

    public T get() {
        return value;
    }
}
