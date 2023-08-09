package com.github.mjkuranda.spaceadventure2.states.menus;

import com.github.mjkuranda.spaceadventure2.PlayerStatistics;
import com.github.mjkuranda.spaceadventure2.resources.GameFont;
import com.github.mjkuranda.spaceadventure2.states.StatesId;
import com.github.mjkuranda.spaceadventure2.states.menus.options.MenuOption;
import com.github.mjkuranda.spaceadventure2.states.menus.options.SimpleMenuOption;
import com.github.mjkuranda.spaceadventure2.states.menus.options.events.EnterStateMenuOptionEvent;
import com.github.mjkuranda.spaceadventure2.states.menus.options.events.decorators.ResetPlayerStatisticsEvent;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class GameOverMenuState extends MenuState {

    public GameOverMenuState(StateBasedGame game) {
        super();

        this.bindOptions(new MenuOption[] {
                new SimpleMenuOption("Play again")
                        .bindOnSelectEvent(
                                new ResetPlayerStatisticsEvent(
                                        new EnterStateMenuOptionEvent(game, this, StatesId.GAME))),
                new SimpleMenuOption("Back to main menu")
                        .bindOnSelectEvent(
                                new ResetPlayerStatisticsEvent(
                                        new EnterStateMenuOptionEvent(game, this, StatesId.MAIN_MENU)))
        });
    }

    @Override
    public int getID() {
        return StatesId.GAME_OVER_MENU;
    }

    @Override
    public void renderPartials(GameContainer container, Graphics g) {
        renderPartial(container, g, "GAME OVER", 200);
        renderPartial(container, g, PlayerStatistics.getInstance().getName(), 256);
        renderPartial(container, g, "Your score: " + PlayerStatistics.getInstance().getScore(), 280);
    }

    private void renderPartial(GameContainer container, Graphics g, String str, float y) {
        if (str == null) {
            return;
        }

        float x = (float) container.getWidth() / 2 - (float) GameFont.VCR_OSD_MONO.getWidth(str) / 2;
        g.drawString(str, x, y);
    }
}
