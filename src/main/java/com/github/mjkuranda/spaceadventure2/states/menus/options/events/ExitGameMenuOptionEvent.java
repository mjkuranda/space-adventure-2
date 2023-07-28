package com.github.mjkuranda.spaceadventure2.states.menus.options.events;

import org.newdawn.slick.Input;

public class ExitGameMenuOptionEvent implements MenuOptionEvent {

    @Override
    public void onSelect() {
        System.exit(0);
    }

    @Override
    public void onChange(int direction) {}

    @Override
    public void onChange(Input input) {}
}
