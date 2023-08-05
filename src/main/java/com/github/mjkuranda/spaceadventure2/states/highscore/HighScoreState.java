package com.github.mjkuranda.spaceadventure2.states.highscore;

import com.github.mjkuranda.spaceadventure2.states.StatesId;
import com.github.mjkuranda.spaceadventure2.states.menus.MenuState;
import com.github.mjkuranda.spaceadventure2.states.menus.options.MenuOption;
import com.github.mjkuranda.spaceadventure2.states.menus.options.SimpleMenuOption;
import com.github.mjkuranda.spaceadventure2.states.menus.options.events.EnterStateMenuOptionEvent;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class HighScoreState extends MenuState {

    public HighScoreState(StateBasedGame game) {
        super();

        this.bindOptions(new MenuOption[] {
                new SimpleMenuOption("Back to the menu")
                        .bindOnSelectEvent(new EnterStateMenuOptionEvent(game, this, StatesId.MAIN_MENU))
        });
    }

    @Override
    public int getID() {
        return StatesId.HIGH_SCORE;
    }

    @Override
    public void renderPartials(GameContainer container, Graphics g) {

    }
}
