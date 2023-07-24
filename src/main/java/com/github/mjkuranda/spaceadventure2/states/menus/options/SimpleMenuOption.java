package com.github.mjkuranda.spaceadventure2.states.menus.options;

public class SimpleMenuOption extends MenuOption {

    public SimpleMenuOption(String title) {
        super(title);
    }

    @Override
    public void onSelect() {
        System.out.println("XXXX");
    }

    @Override
    public void onChange(int direction) {}
}
