package com.github.mjkuranda.spaceadventure2.states.menus.options.events.decorators;

import com.github.mjkuranda.spaceadventure2.GameData;
import com.github.mjkuranda.spaceadventure2.states.menus.options.events.MenuOptionEvent;
import org.newdawn.slick.Input;

public class ResetPlayerStatisticsEvent extends MenuOptionEventDecorator {

    private MenuOptionEvent event;

    public ResetPlayerStatisticsEvent(MenuOptionEvent event) {
        this.event = event;
    }

    @Override
    public void onSelect() {
        event.onSelect();
        GameData.startNewGame();
    }

    @Override
    public void onChange(int direction) {}

    @Override
    public void onChange(Input input) {}
}
