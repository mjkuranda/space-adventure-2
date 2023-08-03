package com.github.mjkuranda.spaceadventure2.states.menus.options.events;

import com.github.mjkuranda.spaceadventure2.PlayerStatistics;
import com.github.mjkuranda.spaceadventure2.states.menus.options.values.StringMenuOptionValue;
import org.newdawn.slick.state.StateBasedGame;

public class NewGameMenuOptionEvent extends EnterStateMenuOptionEvent {

    private StringMenuOptionValue playerName;

    public NewGameMenuOptionEvent(StateBasedGame game, int stateId, StringMenuOptionValue playerName) {
        super(game, stateId);

        this.playerName = playerName;
    }

    @Override
    public void onSelect() {
        super.onSelect();

        PlayerStatistics.getInstance().setName(playerName.get());
    }
}
