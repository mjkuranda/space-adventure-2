package com.github.mjkuranda.spaceadventure2.states.menus.options.events;

import com.github.mjkuranda.spaceadventure2.PlayerStatistics;
import com.github.mjkuranda.spaceadventure2.states.menus.MenuState;
import com.github.mjkuranda.spaceadventure2.states.menus.options.values.StringMenuOptionValue;
import org.newdawn.slick.state.StateBasedGame;

public class NewGameMenuOptionEvent extends EnterStateMenuOptionEvent {

    private MenuState menu;

    private StringMenuOptionValue playerName;

    public NewGameMenuOptionEvent(StateBasedGame game, int stateId, MenuState menu, StringMenuOptionValue playerName) {
        super(game, menu, stateId);

        this.playerName = playerName;
    }

    @Override
    public void onSelect() {
        PlayerStatistics.getInstance().clear();
        PlayerStatistics.getInstance().setName(playerName.get());

        super.onSelect();
    }
}
