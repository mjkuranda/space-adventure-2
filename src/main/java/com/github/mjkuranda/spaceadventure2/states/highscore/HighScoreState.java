package com.github.mjkuranda.spaceadventure2.states.highscore;

import com.github.mjkuranda.spaceadventure2.states.StatesId;
import com.github.mjkuranda.spaceadventure2.states.menus.MenuState;
import com.github.mjkuranda.spaceadventure2.states.menus.options.MenuOption;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class HighScoreState extends MenuState {

    public HighScoreState(MenuOption[] options) {
        super(options);
    }

    @Override
    public int getID() {
        return StatesId.HIGH_SCORE;
    }

    @Override
    public void renderPartials(GameContainer container, Graphics g) {

    }
}
