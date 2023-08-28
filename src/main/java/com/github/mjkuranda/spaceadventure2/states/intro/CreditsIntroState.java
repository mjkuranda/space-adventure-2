package com.github.mjkuranda.spaceadventure2.states.intro;

import com.github.mjkuranda.spaceadventure2.resources.GameFont;
import com.github.mjkuranda.spaceadventure2.resources.GameImage;
import com.github.mjkuranda.spaceadventure2.resources.GameMusic;
import com.github.mjkuranda.spaceadventure2.states.StatesId;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class CreditsIntroState extends BasicGameState {

    private boolean isInitialized;

    @Override
    public int getID() {
        return StatesId.CREDITS_INTRO;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {

    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        if (!isInitialized) {
            return;
        }

        g.setFont(GameFont.RETRO_GAMING);
        g.setColor(Color.white);

        if (getMusicSeconds() < 6.6f) {
            g.drawImage(GameImage.LOGO, container.getWidth() / 2 - 512 / 2, container.getHeight() / 2 - 192 / 2);

            return;
        }

        if (getMusicSeconds() < 13.5f) {
            renderText(container, g, "Music by Evgeny Bardyuzha");

            return;
        }

        if (getMusicSeconds() < 19.7f) {
            Vector2f v = renderText(container, g, "Created by Marek Kuranda");
            g.fillRect(v.getX() + 237, v.getY() + 3, 2, 4);

            return;
        }

        if (getMusicSeconds() < 26.f) {
            renderText(container, g, "Dedicating this game to my dad for his support and motivation in developing this game");

            return;
        }

        game.enterState(StatesId.MAIN_MENU);
        game.getState(StatesId.MAIN_MENU).init(game.getContainer(), game);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if (!isInitialized) {
            isInitialized = true;
            GameMusic.BACKGROUND_MUSIC.loop(1.0f, 0.30f);
        }
    }

    private Vector2f renderText(GameContainer container, Graphics g, String text) {
        Vector2f v = new Vector2f(
                container.getWidth() / 2.f - GameFont.RETRO_GAMING.getWidth(text) / 2.f,
                container.getHeight() / 2.f - GameFont.RETRO_GAMING.getHeight(text) / 2.f
        );

        g.drawString(text, v.getX(), v.getY());

        return v;
    }

    private float getMusicSeconds() {
        return GameMusic.BACKGROUND_MUSIC.getPosition();
    }
}
