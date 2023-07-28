package com.github.mjkuranda.spaceadventure2.states.menus.options.events;

import org.newdawn.slick.Input;

public interface MenuOptionEvent {
    /***
     * Executes a specific task for menu option
     */
    void onSelect();

    /***
     * Changes current value
     * @param direction -1 left, 1 right
     */
    void onChange(int direction);

    /***
     * Changes current value
     * @param input org.newdawn.slick.Input
     */
    void onChange(Input input);
}
