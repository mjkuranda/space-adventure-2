package com.github.mjkuranda.spaceadventure2.states.menus.options.events;

import com.github.mjkuranda.spaceadventure2.PlayerStatistics;
import com.github.mjkuranda.spaceadventure2.states.menus.MenuState;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

public class EnterStateMenuOptionEvent implements MenuOptionEvent {

    private StateBasedGame game;
    private MenuState menu;
    private int stateId;

    public EnterStateMenuOptionEvent(StateBasedGame game, MenuState menu, int stateId) {
        this.game = game;
        this.menu = menu;
        this.stateId = stateId;
    }

    @Override
    public void onSelect() {
        game.enterState(stateId);
        menu.reset();
//        PlayerStatistics.getInstance().clear();
    }

    @Override
    public void onChange(int direction) {}

    @Override
    public void onChange(Input input) {}
}
