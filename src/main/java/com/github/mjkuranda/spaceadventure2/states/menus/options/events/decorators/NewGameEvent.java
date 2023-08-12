package com.github.mjkuranda.spaceadventure2.states.menus.options.events.decorators;

import com.github.mjkuranda.spaceadventure2.GameData;
import com.github.mjkuranda.spaceadventure2.PlayerData;
import com.github.mjkuranda.spaceadventure2.states.menus.options.events.MenuOptionEvent;
import com.github.mjkuranda.spaceadventure2.states.menus.options.values.StringMenuOptionValue;
import org.newdawn.slick.Input;

public class NewGameEvent extends MenuOptionEventDecorator {

    private MenuOptionEvent event;

    private StringMenuOptionValue playerName;

    public NewGameEvent(MenuOptionEvent event, StringMenuOptionValue playerName) {
        this.event = event;
        this.playerName = playerName;
    }

    @Override
    public void onSelect() {
        String playerName = this.playerName.get().length() > 0 ? this.playerName.get() : "Noname";

        PlayerData.getInstance().setName(playerName);
        GameData.startNewGame();
        event.onSelect();
    }

    @Override
    public void onChange(int direction) {}

    @Override
    public void onChange(Input input) {}
}
