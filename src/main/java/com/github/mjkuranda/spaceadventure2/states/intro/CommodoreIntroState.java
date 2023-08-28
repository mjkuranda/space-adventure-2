package com.github.mjkuranda.spaceadventure2.states.intro;

import com.github.mjkuranda.spaceadventure2.SpaceAdventure2;
import com.github.mjkuranda.spaceadventure2.resources.GameFont;
import com.github.mjkuranda.spaceadventure2.resources.GameImage;
import com.github.mjkuranda.spaceadventure2.states.StatesId;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class CommodoreIntroState extends BasicGameState {

    private static final Color COMMODORE_BACKGROUND_COLOR = new Color(165, 165, 255);
    private static final Color COMMODORE_BLUE_BACKGROUND_COLOR = new Color(66, 66, 231);

    private boolean hasStartedRendering;

    private float screenX, screenY;

    public CommodoreIntroState(StateBasedGame game) {
        // Commodore screen grid 40 x 25, block is 16 x 16
        screenX = game.getContainer().getWidth() / 2.f - 20.f * 16.f;
        screenY = game.getContainer().getHeight() / 2.f - 12.5f * 16.f;
    }

    @Override
    public int getID() {
        return StatesId.COMMODORE_INTRO;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {

    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        if (!hasStartedRendering) {
            hasStartedRendering = true;

            return;
        }

        renderCommodoreBackgroundScreen(container, g);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

    }

    private void renderCommodoreBackgroundScreen(GameContainer container, Graphics g) {
        g.setColor(COMMODORE_BACKGROUND_COLOR);
        g.fillRect(0, 0, container.getWidth(), container.getHeight());

        g.setColor(Color.white);
        g.setFont(GameFont.COMMODORE_64);
        g.drawString("TEST IT", screenX, screenY);

        g.setColor(Color.black);
        g.fillRect(screenX, screenY, 16, 16);
    }
}
