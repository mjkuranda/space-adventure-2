package com.github.mjkuranda.spaceadventure2.states.menus.options.events;

import org.newdawn.slick.state.StateBasedGame;

public class EnterStateMenuOptionEvent implements MenuOptionEvent {

    private StateBasedGame game;
    private int stateId;

    public EnterStateMenuOptionEvent(StateBasedGame game, int stateId) {
        this.game = game;
        this.stateId = stateId;
    }

    @Override
    public void onSelect() {
        game.enterState(stateId);
    }

    @Override
    public void onChange(int direction) {}
}
