package com.github.mjkuranda.spaceadventure2.states.intro;

import com.github.mjkuranda.spaceadventure2.resources.GameImage;
import com.github.mjkuranda.spaceadventure2.resources.GameMusic;
import com.github.mjkuranda.spaceadventure2.states.StatesId;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
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

        if (getMusicSeconds() < 6.8f) {
            g.drawImage(GameImage.LOGO, container.getWidth() / 2 - 512 / 2, container.getHeight() / 2 - 192 / 2);

            return;
        }

        if (getMusicSeconds() < 13.5f) {
            g.drawString("Music by Evgeny Bardyuzha", 100, 100);

            return;
        }

        if (getMusicSeconds() < 19.7f) {
            g.drawString("Created by Marek Kuranda", 100, 100);

            return;
        }

        if (getMusicSeconds() < 26.f) {
            g.drawString("Dedicating this game to my dad for his support and motivation in developing this game", 100, 100);

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

    private float getMusicSeconds() {
        return GameMusic.BACKGROUND_MUSIC.getPosition();
    }
}
