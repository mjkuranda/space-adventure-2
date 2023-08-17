package com.github.mjkuranda.spaceadventure2.states;

import com.github.mjkuranda.spaceadventure2.resources.GameAnimation;
import com.github.mjkuranda.spaceadventure2.resources.GameFont;
import com.github.mjkuranda.spaceadventure2.resources.GameImage;
import com.github.mjkuranda.spaceadventure2.resources.GameSound;
import com.github.mjkuranda.spaceadventure2.states.highscore.HighScoreHandler;
import com.github.mjkuranda.spaceadventure2.states.highscore.HighScoreRecord;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.*;
import java.io.IOException;

public class LoadingState extends BasicGameState {

    private boolean areImagesLoaded, areFontsLoaded, areAnimationsLoaded, areHighScoreRecordsLoaded, areSoundsLoaded;

    @Override
    public int getID() {
        return StatesId.LOADING;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {}

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        String str = "Loading " + getLoadingResourceName() + "...";

        int strWidth = g.getFont().getWidth(str);
        int strHeight = g.getFont().getHeight(str);
        float x = (float) container.getWidth() / 2 - (float) strWidth / 2;
        float y = (float) container.getHeight() / 2 - (float) strHeight / 2;

        g.drawString(str, x, y);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if (!areImagesLoaded) {
            areImagesLoaded = true;
            GameImage.load();

            return;
        }

        if (!areFontsLoaded) {
            areFontsLoaded = true;
            try {
                GameFont.load();
            } catch (IOException | FontFormatException e) {
                throw new RuntimeException(e);
            }

            return;
        }

        if (!areAnimationsLoaded) {
            areAnimationsLoaded = true;
            GameAnimation.load();

            return;
        }

        if (!areHighScoreRecordsLoaded) {
            areHighScoreRecordsLoaded = true;
            HighScoreHandler.getInstance().fetchRecords();

            return;
        }

        if (!areSoundsLoaded) {
            areSoundsLoaded = true;
            GameSound.load();

            return;
        }

        game.enterState(StatesId.MAIN_MENU);
    }

    private String getLoadingResourceName() {
        if (!areImagesLoaded) {
            return "images";
        }

        if (!areFontsLoaded) {
            return "fonts";
        }

        if (!areAnimationsLoaded) {
            return "animations";
        }

        if (!areHighScoreRecordsLoaded) {
            return "high scores";
        }

        if (!areSoundsLoaded) {
            return "sounds";
        }

        return "completed";
    }
}
