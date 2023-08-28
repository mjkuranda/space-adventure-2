package com.github.mjkuranda.spaceadventure2.states.intro;

import com.github.mjkuranda.spaceadventure2.SpaceAdventure2;
import com.github.mjkuranda.spaceadventure2.resources.GameFont;
import com.github.mjkuranda.spaceadventure2.resources.GameImage;
import com.github.mjkuranda.spaceadventure2.resources.GameSound;
import com.github.mjkuranda.spaceadventure2.states.StatesId;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class CommodoreIntroState extends BasicGameState {

    private static final Color COMMODORE_BACKGROUND_COLOR = new Color(165, 165, 255);
    private static final Color COMMODORE_BLUE_BACKGROUND_COLOR = new Color(66, 66, 231);

    private static final int SCREEN_GRID_X = 40;
    private static final int SCREEN_GRID_Y = 25;

    private static final int SCREEN_GRID_SIZE = 16;

    // FIXME: 2000
    private final long AWAITING_TIME = 1;

    private boolean hasStartedRendering;
    private long startTime;
    private String gameText;
    private boolean isInputtedCommand;
    private boolean isSearching;
    private boolean isFoundGame;

    private final String gameTextFull;

    private final float screenX;
    private final float screenY;

    public CommodoreIntroState(StateBasedGame game) {
        // Commodore screen grid 40 x 25, block is 16 x 16
        screenX = game.getContainer().getWidth() / 2.f - SCREEN_GRID_X / 2.f * SCREEN_GRID_SIZE;
        screenY = game.getContainer().getHeight() / 2.f - SCREEN_GRID_Y / 2.f * SCREEN_GRID_SIZE;

        gameText = "";
        gameTextFull = "LOAD \"SPACE ADVENTURE\"";
    }

    @Override
    public int getID() {
        return StatesId.COMMODORE_INTRO;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {

    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        if (!hasStartedRendering) {
            hasStartedRendering = true;
            startTime = System.currentTimeMillis();

            return;
        }

        renderCommodoreBackgroundScreen(container, g);
        renderScreenText(g);
        renderGameText(g);

        if (isSearchingGame()) {
            renderBlankScreen(container, g);
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        long time = System.currentTimeMillis() - startTime;

        if (time < AWAITING_TIME) {
            return;
        }

        String newGameText = getGameText(time);

        if (gameText.length() != newGameText.length()) {
            gameText = newGameText;

            switch (gameText.length()) {
                case 5:
                case 12:
                    GameSound.KEY_SPACE_BAR.play();
                    break;
                default:
                    GameSound.playKeySound();
                    break;
            }

            return;
        }

        if (isGameTextFullWritten() && !isInputtedCommand) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            GameSound.KEY_BACKSPACE.play();
            isInputtedCommand = true;

            return;
        }

        if (isInputtedCommand && !isSearching) {
            isSearching = true;

            // Inputted
            try {
                GameSound.KEY_BACKSPACE.play();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            return;
        }

        if (isSearchingGame()) {
            // Searching
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            isSearching = false;
            isFoundGame = true;
        }
    }

    private void renderCommodoreBackgroundScreen(GameContainer container, Graphics g) {
        g.setColor(COMMODORE_BACKGROUND_COLOR);
        g.fillRect(0, 0, container.getWidth(), container.getHeight());

        g.setColor(COMMODORE_BLUE_BACKGROUND_COLOR);
        g.fillRect(screenX, screenY, SCREEN_GRID_X * SCREEN_GRID_SIZE, SCREEN_GRID_Y * SCREEN_GRID_SIZE);
    }

    private void renderScreenText(Graphics g) {
        g.setFont(GameFont.COMMODORE_64);
        g.setColor(COMMODORE_BACKGROUND_COLOR);
        g.drawString("    **** NG MARKO RETROCOMPUTER ****    ", screenX, screenY + 16);
        g.drawString(" 64K RAM SYSTEM  38911 BASIC BYTES FREE ", screenX, screenY + 16 * 3);
        g.drawString("READY.", screenX, screenY + 16 * 5);
    }

    private void renderGameText(Graphics g) {
        g.drawString(gameText, screenX, screenY + 16 * 7);

        if (isInputtedCommand) {
            g.drawString("PRESS PLAY ON TAPE", screenX, screenY + 16 * 9);
        }

        if (isFoundGame) {
            g.drawString("SEARCHING", screenX, screenY + 16 * 11);
            g.drawString("FOUND SPACE ADVENTURE 2", screenX, screenY + 16 * 12);
        }
    }

    private void renderBlankScreen(GameContainer container, Graphics g) {
        g.setColor(COMMODORE_BACKGROUND_COLOR);
        g.fillRect(0, 0, container.getWidth(), container.getHeight());
    }

    private String getGameText(long time) {
        long size = (time - AWAITING_TIME) / 250;

        if (size > gameTextFull.length()) {
            return gameTextFull;
        }

        return gameTextFull.substring(0, (int) size);
    }

    private boolean isGameTextFullWritten() {
        return gameTextFull.length() == gameText.length();
    }

    private boolean isSearchingGame() {
        return isSearching && !isFoundGame;
    }
}
