package com.github.mjkuranda.spaceadventure2;

import org.newdawn.slick.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SpaceAdventure2 extends BasicGame {

    private static final String GAME_TITLE = "Space Adventure II";

    public SpaceAdventure2() {
        super(GAME_TITLE);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {

    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {

    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {

    }

    public static void main(String[] args) {
        try {
            AppGameContainer appgc;
            appgc = new AppGameContainer(new SpaceAdventure2());
            appgc.setDisplayMode(640, 480, false);
            appgc.start();
        } catch (SlickException ex) {
            Logger.getLogger(SpaceAdventure2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
