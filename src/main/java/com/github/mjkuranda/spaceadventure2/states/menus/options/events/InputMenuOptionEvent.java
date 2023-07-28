package com.github.mjkuranda.spaceadventure2.states.menus.options.events;

import com.github.mjkuranda.spaceadventure2.states.menus.options.values.StringMenuOptionValue;
import org.newdawn.slick.Input;

public class InputMenuOptionEvent implements MenuOptionEvent {

    private StringMenuOptionValue value;

    public InputMenuOptionEvent(StringMenuOptionValue value) {
        this.value = value;
    }

    @Override
    public void onSelect() {}

    @Override
    public void onChange(int direction) {}

    @Override
    public void onChange(Input input) {
        System.out.println(input);

        value.update(value.get() + "x");
    }
}
