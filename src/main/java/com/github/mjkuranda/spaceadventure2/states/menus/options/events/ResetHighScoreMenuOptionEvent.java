package com.github.mjkuranda.spaceadventure2.states.menus.options.events;

import com.github.mjkuranda.spaceadventure2.states.highscore.HighScoreHandler;
import org.newdawn.slick.Input;

public class ResetHighScoreMenuOptionEvent implements MenuOptionEvent {

    @Override
    public void onSelect() {
        HighScoreHandler.getInstance().resetRecords();
    }

    @Override
    public void onChange(int direction) {}

    @Override
    public void onChange(Input input) {}
}
