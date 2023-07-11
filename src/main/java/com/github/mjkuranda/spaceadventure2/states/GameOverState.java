package com.github.mjkuranda.spaceadventure2.states;

import com.github.mjkuranda.spaceadventure2.PlayerStatistics;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class GameOverState extends BasicGameState {

    /** Final player statistics */
    private PlayerStatistics stats;

    public GameOverState() {
        stats = PlayerStatistics.getInstance();
    }

    @Override
    public int getID() {
        return StatesId.GAME_OVER;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {

    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.drawString("Your score: " + stats.getScore(), 256, 256);
        g.drawString("Press 1 to start game again!", 256, 288);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        Input in = container.getInput();

        if (in.isKeyPressed(Input.KEY_1)) {
            game.enterState(StatesId.GAME, new FadeOutTransition(), new FadeInTransition());
            stats.clear();
        }
    }
}
