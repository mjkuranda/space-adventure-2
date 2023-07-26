package com.github.mjkuranda.spaceadventure2;

import com.github.mjkuranda.spaceadventure2.states.GameOverState;
import com.github.mjkuranda.spaceadventure2.states.GameState;
import com.github.mjkuranda.spaceadventure2.states.IntroState;
import com.github.mjkuranda.spaceadventure2.states.menus.MainMenuState;
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
        addState(new MainMenuState(this));
        addState(new GameState());
        addState(new GameOverState());
        addState(new IntroState());

        GameImages images = GameImages.getInstance();
        images.init();
    }

    public static void main(String[] args) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        ScalableGame sg = new ScalableGame(new SpaceAdventure2(), screenSize.width, screenSize.height, true);
        AppGameContainer appgc;

        try {
            appgc = new AppGameContainer(sg);
            appgc.setDisplayMode(screenSize.width, screenSize.height, false);
            appgc.setShowFPS(false);
            appgc.setTargetFrameRate(60);
            appgc.setFullscreen(true);

            appgc.start();
        } catch (SlickException ex) {
            Logger.getLogger(SpaceAdventure2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
