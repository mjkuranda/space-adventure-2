package com.github.mjkuranda.spaceadventure2.states.menus;

import com.github.mjkuranda.spaceadventure2.resources.GameFont;
import com.github.mjkuranda.spaceadventure2.states.StatesId;
import com.github.mjkuranda.spaceadventure2.states.menus.options.MenuOption;
import com.github.mjkuranda.spaceadventure2.states.menus.options.SimpleMenuOption;
import com.github.mjkuranda.spaceadventure2.states.menus.options.events.EnterStateMenuOptionEvent;
import com.github.mjkuranda.spaceadventure2.states.menus.options.events.ExitGameMenuOptionEvent;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class MainMenuState extends MenuState {

    public MainMenuState(StateBasedGame game) {
        super(new MenuOption[] {
                new SimpleMenuOption("Start")
                        .bindOnSelectEvent(new EnterStateMenuOptionEvent(game, StatesId.GAME)),
                new SimpleMenuOption("Exit")
                        .bindOnSelectEvent(new ExitGameMenuOptionEvent())
        });
    }

    @Override
    public int getID() {
        return StatesId.MAIN_MENU;
    }

    @Override
    public void renderPartials(GameContainer container, Graphics g) {
        String title = "Space Adventure II";
        int width = GameFont.VCR_OSD_MONO.getWidth(title);
        float textX = (float) container.getWidth() / 2 - (float) width / 2;

        g.setFont(GameFont.VCR_OSD_MONO);
        g.drawString(title, textX, 300);
    }
}
