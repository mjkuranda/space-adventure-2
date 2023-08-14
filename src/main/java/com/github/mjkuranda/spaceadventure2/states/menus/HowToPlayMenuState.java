package com.github.mjkuranda.spaceadventure2.states.menus;

import com.github.mjkuranda.spaceadventure2.states.StatesId;
import com.github.mjkuranda.spaceadventure2.states.highscore.HighScoreHandler;
import com.github.mjkuranda.spaceadventure2.states.menus.options.MenuOption;
import com.github.mjkuranda.spaceadventure2.states.menus.options.SimpleMenuOption;
import com.github.mjkuranda.spaceadventure2.states.menus.options.events.EnterStateMenuOptionEvent;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class HowToPlayMenuState extends MenuState {

    private float optionX;

    private float optionY;

    public HowToPlayMenuState(StateBasedGame game) {
        super();

        this.bindOptions(new MenuOption[] {
                new SimpleMenuOption("Back to the menu")
                        .bindOnSelectEvent(new EnterStateMenuOptionEvent(game, this, StatesId.MAIN_MENU))
        });

        this.optionX = game.getContainer().getWidth() / 2.0f - MenuOption.OPTION_WIDTH / 2.0f;
        this.optionY = game.getContainer().getHeight() - 256;
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        renderPartials(container, g);

        for (int i = 0; i < options.length; i++) {
            options[i].render(container, g, i, currentOption, options.length, optionX, optionY);
        }
    }

    @Override
    public int getID() {
        return StatesId.HOW_TO_PLAY;
    }

    @Override
    public void renderPartials(GameContainer container, Graphics g) {

    }
}
