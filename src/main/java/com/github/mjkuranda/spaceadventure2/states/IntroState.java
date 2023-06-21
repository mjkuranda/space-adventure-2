package com.github.mjkuranda.spaceadventure2.states;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class IntroState extends BasicGameState {
    @Override
    public int getID() {
        return StatesId.INTRO;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {

    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.setColor(Color.blue);
        g.fillRect(0, 0, 1500, 1500);

        g.setColor(Color.white);
        g.drawString("PLAY", 20, 20);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        Input in = container.getInput();

        if (in.isKeyPressed(Input.KEY_1)) {
            game.enterState(StatesId.GAME, new FadeOutTransition(), new FadeInTransition());
        }
    }
}
