package com.github.mjkuranda.spaceadventure2.states;

import com.github.mjkuranda.spaceadventure2.entities.Asteroid;
import com.github.mjkuranda.spaceadventure2.entities.EntityType;
import com.github.mjkuranda.spaceadventure2.map.GameMap;
import com.github.mjkuranda.spaceadventure2.renderers.ArcadeRenderer;
import com.github.mjkuranda.spaceadventure2.renderers.Renderer;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.Random;

public class GameState extends BasicGameState {

    /** Game map */
    private GameMap map;

    /** Game map renderer */
    private Renderer renderer;

    public GameState() {
        map = new GameMap();
        renderer = new ArcadeRenderer(map);
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
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        map.update();

        Input in = container.getInput();

        if (in.isKeyPressed(Input.KEY_F)) {
            container.setFullscreen(!container.isFullscreen());
        }

        float prob = new Random().nextFloat();

        if (prob < 0.05) {
            map.spawn(EntityType.ASTEROID);
        }
    }
}
