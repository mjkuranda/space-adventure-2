package com.github.mjkuranda.spaceadventure2.states.menus.options.events;

public class ExitGameMenuOptionEvent implements MenuOptionEvent {

    @Override
    public void onSelect() {
        System.exit(0);
    }

    @Override
    public void onChange(int direction) {

    }
}
