package com.github.mjkuranda.spaceadventure2.states.menus;

import com.github.mjkuranda.spaceadventure2.states.StatesId;
import com.github.mjkuranda.spaceadventure2.states.menus.options.InputMenuOption;
import com.github.mjkuranda.spaceadventure2.states.menus.options.MenuOption;
import com.github.mjkuranda.spaceadventure2.states.menus.options.SimpleMenuOption;
import com.github.mjkuranda.spaceadventure2.states.menus.options.events.EnterStateMenuOptionEvent;
import com.github.mjkuranda.spaceadventure2.states.menus.options.events.InputMenuOptionEvent;
import com.github.mjkuranda.spaceadventure2.states.menus.options.values.StringMenuOptionValue;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class GameOverMenuState extends MenuState {

    private StringMenuOptionValue playerName;

    public GameOverMenuState(StateBasedGame game) {
        super(null);

        playerName = new StringMenuOptionValue("");

        super.bindOptions(new MenuOption[] {
                new InputMenuOption(playerName)
                        .bindOnChangeEvent(new InputMenuOptionEvent(playerName)),
                new SimpleMenuOption("Back to main menu")
                        .bindOnSelectEvent(new EnterStateMenuOptionEvent(game, StatesId.MAIN_MENU))
        });
    }

    @Override
    public int getID() {
        return StatesId.GAME_OVER_MENU;
    }

    @Override
    public void renderPartials(GameContainer container, Graphics g) {
        g.drawString(playerName.get(), 100, 100);
    }
}
