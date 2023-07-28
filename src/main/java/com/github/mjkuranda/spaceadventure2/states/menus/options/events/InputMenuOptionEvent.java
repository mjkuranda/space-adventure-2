package com.github.mjkuranda.spaceadventure2.states.menus.options.events;

import org.newdawn.slick.Input;

public class InputMenuOptionEvent implements MenuOptionEvent {

    private String value;

    public InputMenuOptionEvent(String value) {
        this.value = value;
    }

    @Override
    public void onSelect() {}

    @Override
    public void onChange(int direction) {}

    @Override
    public void onChange(Input input) {
        System.out.println(input);

        value = value + "x";
    }
}
