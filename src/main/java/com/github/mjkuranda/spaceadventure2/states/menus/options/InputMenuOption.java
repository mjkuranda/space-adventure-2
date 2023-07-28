package com.github.mjkuranda.spaceadventure2.states.menus.options;

import com.github.mjkuranda.spaceadventure2.states.menus.options.values.StringMenuOptionValue;

public class InputMenuOption extends MenuOption {

    /** Current value reference */
    private StringMenuOptionValue value;

    public InputMenuOption(StringMenuOptionValue value) {
        super("");

        this.value = value;
    }
}
