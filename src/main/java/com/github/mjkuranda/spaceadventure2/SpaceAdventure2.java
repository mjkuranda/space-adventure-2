package com.github.mjkuranda.spaceadventure2;

import com.github.mjkuranda.spaceadventure2.states.GameState;
import com.github.mjkuranda.spaceadventure2.states.LoadingState;
import com.github.mjkuranda.spaceadventure2.states.highscore.HighScoreState;
import com.github.mjkuranda.spaceadventure2.states.intro.CommodoreIntroState;
import com.github.mjkuranda.spaceadventure2.states.intro.CreditsIntroState;
import com.github.mjkuranda.spaceadventure2.states.menus.*;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SpaceAdventure2 extends StateBasedGame {

    public static final String GAME_TITLE   = "Space Adventure II";
    public static final String GAME_VERSION = "v1.0.1";
    public static final String GAME_CREATOR = "Marek Kuranda";

    public SpaceAdventure2() {
        super(GAME_TITLE);
    }

    @Override
    public void initStatesList(GameContainer container) throws SlickException {
        addState(new LoadingState());
        addState(new CommodoreIntroState(this));
        addState(new CreditsIntroState());
        addState(new MainMenuState(this));
        addState(new NewGameMenuState(this));
        addState(new GameState());
        addState(new GameOverMenuState(this));
        addState(new HighScoreState(this));
        addState(new HowToPlayMenuState(this));
        addState(new CreditsMenuState(this));
    }

    public static void main(String[] args) {
        DisplayMode displayMode = Display.getDesktopDisplayMode();
        ScalableGame sg = new ScalableGame(new SpaceAdventure2(), displayMode.getWidth(), displayMode.getHeight(), true);
        AppGameContainer appgc;

        try {
            appgc = new AppGameContainer(sg);
            appgc.setDisplayMode(displayMode.getWidth(), displayMode.getHeight(), false);
            appgc.setShowFPS(false);
            appgc.setTargetFrameRate(60);
            appgc.setFullscreen(true);

            appgc.start();
        } catch (SlickException ex) {
            Logger.getLogger(SpaceAdventure2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
