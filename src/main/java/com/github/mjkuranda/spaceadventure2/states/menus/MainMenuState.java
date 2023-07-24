package com.github.mjkuranda.spaceadventure2.states.menus;

import com.github.mjkuranda.spaceadventure2.states.StatesId;
import com.github.mjkuranda.spaceadventure2.states.menus.options.MenuOption;
import com.github.mjkuranda.spaceadventure2.states.menus.options.SimpleMenuOption;

public class MainMenuState extends MenuState {

    public MainMenuState() {
        super(new MenuOption[] {
                new SimpleMenuOption("Start"),
                new SimpleMenuOption("Exit")
        });
    }

    @Override
    public int getID() {
        return StatesId.MAIN_MENU;
    }
}
