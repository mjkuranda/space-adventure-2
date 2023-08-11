package com.github.mjkuranda.spaceadventure2.states;

import com.github.mjkuranda.spaceadventure2.GameData;
import com.github.mjkuranda.spaceadventure2.PlayerData;
import com.github.mjkuranda.spaceadventure2.renderers.ArcadeRenderer;
import com.github.mjkuranda.spaceadventure2.renderers.Renderer;
import com.github.mjkuranda.spaceadventure2.renderers.RetroRenderer;
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

    public GameState() {
        data = new GameData();
        stats = PlayerData.getInstance();

        ARCADE_RENDERER = new ArcadeRenderer(data);
        RETRO_RENDERER = new RetroRenderer(data);

        renderer = RETRO_RENDERER;
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
        g.drawString("Score: " + stats.getScore(), container.getWidth() - 256, 16);
        g.drawString("Distance: " + (int) (stats.getDistance()), container.getWidth() - 256, 48);
        g.drawString(stats.getName(), container.getWidth() - 256, 80);

        g.drawString("Remained time: " + GameData.getRemainingTime(), container.getWidth() - 256,  112);
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
