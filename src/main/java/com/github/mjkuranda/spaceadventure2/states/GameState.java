package com.github.mjkuranda.spaceadventure2.states;

import com.github.mjkuranda.spaceadventure2.GameData;
import com.github.mjkuranda.spaceadventure2.PlayerData;
import com.github.mjkuranda.spaceadventure2.entities.Spaceship;
import com.github.mjkuranda.spaceadventure2.renderers.ArcadeRenderer;
import com.github.mjkuranda.spaceadventure2.renderers.Renderer;
import com.github.mjkuranda.spaceadventure2.renderers.RetroRenderer;
import com.github.mjkuranda.spaceadventure2.resources.GameFont;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameState extends BasicGameState {

    private static Renderer ARCADE_RENDERER, RETRO_RENDERER;

    /** Game data */
    private GameData data;

    /** Player statistics */
    private PlayerData stats;

    /** Game data renderer */
    private Renderer renderer;

    /** Game bars */
    private GameBar durabilityBar, gameTimeBar;

    public GameState() {
        data = new GameData();
        stats = PlayerData.getInstance();

        ARCADE_RENDERER = new ArcadeRenderer(data);
        RETRO_RENDERER = new RetroRenderer(data);

        renderer = RETRO_RENDERER;

        durabilityBar = new GameBar("Durability", Spaceship.DURABILITY);
        gameTimeBar = new LongGameBar("Time to end", GameData.GAME_TIME_LENGTH);
    }

    @Override
    public int getID() {
        return StatesId.GAME;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {

    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        renderer.render(g);
        renderUI(container, g);
    }

    private void renderUI(GameContainer container, Graphics g) {
        g.setColor(Color.white);
        g.setFont(GameFont.VCR_OSD_MONO);

        String playerName = stats.getName();
        g.drawString(playerName, container.getWidth() / 2 - GameFont.VCR_OSD_MONO.getWidth(playerName) / 2, 16);
        String scoreText = "Score: " + stats.getScore();
        g.drawString(scoreText, container.getWidth() / 2 - GameFont.VCR_OSD_MONO.getWidth(scoreText) / 2, 48);
        g.drawString("Missile count: " + stats.getMissileCount(), container.getWidth() - durabilityBar.getWidth() - 32, 96);


        durabilityBar.render(g, data.getPlayer().getDurability(), container.getWidth() - durabilityBar.getWidth() - 32, 32);
        gameTimeBar.render(g, GameData.getRemainingTime(), container.getWidth() / 2 - gameTimeBar.getWidth() / 2, container.getHeight() - gameTimeBar.getHeight() - 32);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        data.update(container, game);

        Input in = container.getInput();

        if (in.isKeyPressed(Input.KEY_F)) {
            container.setFullscreen(!container.isFullscreen());
        }

        if (in.isKeyPressed(Input.KEY_M)) {
            renderer.toggleMesh();
        }

        /** Handle renderers */
        if (in.isKeyPressed(Input.KEY_1)) {
            renderer = ARCADE_RENDERER;
        }

        if (in.isKeyPressed(Input.KEY_2)) {
            renderer = RETRO_RENDERER;
        }
    }
}

class LongGameBar extends GameBar {
    public LongGameBar(String label, int maxValue) {
        super(label, maxValue);

        this.squareCount = 25;
    }
}

class GameBar {

    private static final int SQUARE_WIDTH = 16;
    private static final int SQUARE_HEIGHT = 32;

    private static final int SQUARE_SPACE = 2;

    protected int squareCount = 10;

    /** Description what this bar is */
    private final String label;

    /** Count of squares in bar */
    private final int maxValue;

    public GameBar(String label, int maxValue) {
        this.label = label;
        this.maxValue = maxValue;
    }

    public int getWidth() {
        return squareCount * SQUARE_WIDTH + (squareCount - 1) * SQUARE_SPACE + 1;
    }

    public int getHeight() {
        return SQUARE_HEIGHT + 1;
    }

    private Color getColor(int value) {
        float ratio = (float) value / (float) maxValue;

        if (ratio < 0.33) {
            return Color.red;
        }

        if (ratio < 0.66) {
            return Color.yellow;
        }

        return Color.green;
    }

    private float getFulfillmentRatio(int value) {
        float ratio = (float) value / (float) maxValue;

        return Math.round(ratio * 100.0f) / 100.0f;
    }

    public <T extends Number> void render(Graphics g, T value, int x, int y) {
        // Draw a border
        g.setColor(Color.white);
        g.drawRect(x, y, getWidth(), getHeight());

        // Draw squares
        int val = value.intValue();
        g.setColor(getColor(val));
        int squares = (int) (getFulfillmentRatio(val) * (float) squareCount);
        for (int i = 0; i < squares; i++) {
            int xOffset = (i * SQUARE_WIDTH) + ((i > 0 ? i : 0) * SQUARE_SPACE);

            g.fillRect(x + 1 + xOffset, y + 1, SQUARE_WIDTH, SQUARE_HEIGHT);
        }

        // Draw a label
        int strSize = GameFont.VCR_OSD_MONO.getWidth(label);
        g.setColor(Color.white);
        g.drawString(label, x + getWidth() - strSize, y + getHeight());
    }
}