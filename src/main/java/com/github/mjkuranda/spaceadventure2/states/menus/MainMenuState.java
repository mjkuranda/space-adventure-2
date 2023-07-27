package com.github.mjkuranda.spaceadventure2.states.menus;

import com.github.mjkuranda.spaceadventure2.SpaceAdventure2;
import com.github.mjkuranda.spaceadventure2.resources.GameFont;
import com.github.mjkuranda.spaceadventure2.states.StatesId;
import com.github.mjkuranda.spaceadventure2.states.menus.options.MenuOption;
import com.github.mjkuranda.spaceadventure2.states.menus.options.SimpleMenuOption;
import com.github.mjkuranda.spaceadventure2.states.menus.options.events.EnterStateMenuOptionEvent;
import com.github.mjkuranda.spaceadventure2.states.menus.options.events.ExitGameMenuOptionEvent;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.UnicodeFont;
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
        g.setFont(GameFont.VCR_OSD_MONO);
        float fontHeight = GameFont.VCR_OSD_MONO.getHeight("X");

        String title = SpaceAdventure2.GAME_TITLE;
        int width = getStringWidth(title, GameFont.VCR_OSD_MONO);
        float textX = (float) container.getWidth() / 2 - (float) width / 2;

        String version = SpaceAdventure2.GAME_VERSION;
        int versionWidth = getStringWidth(version, GameFont.VCR_OSD_MONO);
        float versionX = (float) container.getWidth() - versionWidth;
        float versionY = (float) container.getHeight() - fontHeight - fontHeight;

        String creator = "Created by: " + SpaceAdventure2.GAME_CREATOR;
        int creatorWidth = getStringWidth(creator, GameFont.VCR_OSD_MONO);
        float creatorX = (float) container.getWidth() - creatorWidth;
        float creatorY = (float) container.getHeight() - fontHeight;

        // Game texts
        g.drawString(title, textX, 300);
        g.drawString(version, versionX, versionY);
        g.drawString(creator, creatorX, creatorY);
        g.fillRect(container.getWidth() - 24, creatorY, 2, 4);
    }

    private int getStringWidth(String str, UnicodeFont font) {
        return font.getWidth(str);
    }
}
