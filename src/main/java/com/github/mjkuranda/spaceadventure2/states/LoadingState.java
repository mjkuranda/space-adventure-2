package com.github.mjkuranda.spaceadventure2.states;

import com.github.mjkuranda.spaceadventure2.resources.GameAnimation;
import com.github.mjkuranda.spaceadventure2.resources.GameFont;
import com.github.mjkuranda.spaceadventure2.resources.GameImage;
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

    private String resourceName = "";

    private boolean areImagesLoaded, areFontsLoaded, areAnimationsLoaded, areHighScoreRecordsLoaded;

    @Override
    public int getID() {
        return StatesId.LOADING;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {}

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        String str = "Loading " + resourceName + "...";

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
            resourceName = "images";
            GameImage.load();

            return;
        }

        if (!areFontsLoaded) {
            areFontsLoaded = true;
            resourceName = "fonts";
            try {
                GameFont.load();
            } catch (IOException | FontFormatException e) {
                throw new RuntimeException(e);
            }

            return;
        }

        if (!areAnimationsLoaded) {
            areAnimationsLoaded = true;
            resourceName = "animations";
            GameAnimation.load();

            return;
        }

        if (!areHighScoreRecordsLoaded) {
            areHighScoreRecordsLoaded = true;
            resourceName = "high scores";
            // FIXME: Remove it
//            HighScoreHandler handler = HighScoreHandler.getInstance();
//            handler.inputRecord(new HighScoreRecord("X", 5, "2023-01-01"));
//            handler.inputRecord(new HighScoreRecord("Y", 3, "2023-01-01"));
//            handler.inputRecord(new HighScoreRecord("Z", 2, "2023-01-01"));
//            handler.inputRecord(new HighScoreRecord("W", 10, "2023-01-01"));
//            handler.inputRecord(new HighScoreRecord("K", 11, "2023-01-01"));
//            handler.inputRecord(new HighScoreRecord("L", 10, "2023-01-01"));
//            handler.inputRecord(new HighScoreRecord("G", 13, "2023-01-01"));
//            handler.inputRecord(new HighScoreRecord("H", 18, "2023-01-01"));
//            handler.inputRecord(new HighScoreRecord("S", 13, "2023-01-01"));
//            handler.inputRecord(new HighScoreRecord("D1234567890123456", 50, "2023-01-01"));
//            handler.inputRecord(new HighScoreRecord("V", 1, "2023-01-01"));
//        System.out.println(handler.getRecords());
//        handler.updateRecords();
            HighScoreHandler.getInstance().fetchRecords();

            return;
        }

        game.enterState(StatesId.HIGH_SCORE);
    }
}
