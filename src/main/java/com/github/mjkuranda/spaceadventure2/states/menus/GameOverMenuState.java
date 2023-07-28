package com.github.mjkuranda.spaceadventure2.states.menus;

import com.github.mjkuranda.spaceadventure2.PlayerStatistics;
import com.github.mjkuranda.spaceadventure2.resources.GameFont;
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
        renderPartial(container, g, "GAME OVER", 200);
        renderPartial(container, g, "Your score: " + PlayerStatistics.getInstance().getScore(), 256);
    }

    private void renderPartial(GameContainer container, Graphics g, String str, float y) {
        float x = (float) container.getWidth() / 2 - (float) GameFont.VCR_OSD_MONO.getWidth(str) / 2;
        g.drawString(str, x, y);
    }
}
