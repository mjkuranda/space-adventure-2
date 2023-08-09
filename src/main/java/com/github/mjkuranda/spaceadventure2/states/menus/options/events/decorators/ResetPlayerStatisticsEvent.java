package com.github.mjkuranda.spaceadventure2.states.menus.options.events.decorators;

import com.github.mjkuranda.spaceadventure2.PlayerStatistics;
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
        PlayerStatistics.getInstance().reset();
    }

    @Override
    public void onChange(int direction) {}

    @Override
    public void onChange(Input input) {}
}
