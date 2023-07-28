package com.github.mjkuranda.spaceadventure2;

import com.github.mjkuranda.spaceadventure2.resources.GameAnimation;
import com.github.mjkuranda.spaceadventure2.resources.GameFont;
import com.github.mjkuranda.spaceadventure2.resources.GameImage;
import com.github.mjkuranda.spaceadventure2.states.GameOverState;
import com.github.mjkuranda.spaceadventure2.states.GameState;
import com.github.mjkuranda.spaceadventure2.states.IntroState;
import com.github.mjkuranda.spaceadventure2.states.menus.GameOverMenuState;
import com.github.mjkuranda.spaceadventure2.states.menus.MainMenuState;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SpaceAdventure2 extends StateBasedGame {

    public static final String GAME_TITLE   = "Space Adventure II";
    public static final String GAME_VERSION = "v0.15.0";
    public static final String GAME_CREATOR = "Marek Kuranda";

    public SpaceAdventure2() {
        super(GAME_TITLE);
    }

    @Override
    public void initStatesList(GameContainer container) throws SlickException {
        // FIXME: Loading resources in `LoadingState` state.
        GameImage.load();
        try {
            GameFont.load();
        } catch (IOException | FontFormatException e) {
            throw new RuntimeException(e);
        }
        GameAnimation.load();

        addState(new GameOverMenuState(this));
        addState(new MainMenuState(this));
        addState(new GameState());
        addState(new GameOverState());
        addState(new IntroState());
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
