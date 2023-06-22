package com.github.mjkuranda.spaceadventure2;

import com.github.mjkuranda.spaceadventure2.states.GameState;
import com.github.mjkuranda.spaceadventure2.states.IntroState;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SpaceAdventure2 extends StateBasedGame {

    private static final String GAME_TITLE = "Space Adventure II";

    public SpaceAdventure2() {
        super(GAME_TITLE);
    }

    @Override
    public void initStatesList(GameContainer container) throws SlickException {
        addState(new IntroState());
        addState(new GameState());
    }

    public static void main(String[] args) {
        try {
            AppGameContainer appgc = new AppGameContainer(new SpaceAdventure2());
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

            appgc.setDisplayMode(screenSize.width, screenSize.height, false);
            appgc.setShowFPS(false);

            appgc.start();
        } catch (SlickException ex) {
            Logger.getLogger(SpaceAdventure2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
