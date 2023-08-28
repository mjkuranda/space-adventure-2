package com.github.mjkuranda.spaceadventure2.renderers;

import com.github.mjkuranda.spaceadventure2.GameData;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class ArcadeRenderer extends Renderer {

    private static final int GAME_OBJECT_SIZE = 32;

    private static final int RENDERER_WIDTH = Display.getDesktopDisplayMode().getWidth();
    private static final int RENDERER_HEIGHT = Display.getDesktopDisplayMode().getHeight();

    private static final int START_X = RENDERER_WIDTH / 2 - (GameData.X_SIZE / 2) * GAME_OBJECT_SIZE;
    private static final int START_Y = RENDERER_HEIGHT / 2 - (GameData.Y_SIZE / 2) * GAME_OBJECT_SIZE;

    public ArcadeRenderer(GameData data) {
        super(data, true);
    }

    @Override
    protected void renderBackground(Graphics g) {
        //
    }

    @Override
    protected void renderMesh(Graphics g) {
        for (int y = 0; y < GameData.Y_SIZE; y++) {
            for (int x = 0; x < GameData.X_SIZE; x++) {
                g.setColor(Color.red);
                g.drawRect(START_X + x * GAME_OBJECT_SIZE, START_Y + y * GAME_OBJECT_SIZE, GAME_OBJECT_SIZE, GAME_OBJECT_SIZE);
            }
        }
    }

    @Override
    protected void renderEntities(Graphics g) {
        /** Render space entities */
        for (var line : getData().getSpaceEntityList()) {
            var it = line.iterator();

            while (it.hasNext()) {
                var entity = it.next();

                g.setColor(Color.gray);
                g.fillRect(START_X + entity.getX() * GAME_OBJECT_SIZE, START_Y + entity.getY() * GAME_OBJECT_SIZE, GAME_OBJECT_SIZE, GAME_OBJECT_SIZE);
            }
        }

        /** Render player missiles */
        for (var missile : getData().getPlayerMissiles()) {
            g.setColor(Color.green);
            g.fillRect(START_X + missile.getX() * GAME_OBJECT_SIZE + 12, START_Y + missile.getY() * GAME_OBJECT_SIZE, 8, GAME_OBJECT_SIZE);
        }

        /** Render player */
        var player = getData().getPlayer();

        g.setColor(Color.red);
        g.fillRect(START_X + player.getX() * GAME_OBJECT_SIZE, START_Y + player.getY() * GAME_OBJECT_SIZE, GAME_OBJECT_SIZE, GAME_OBJECT_SIZE);

        /** Render particles */
        for (var particle : getData().getParticles()) {
            particle.getAnimation().draw(START_X + particle.getX() * GAME_OBJECT_SIZE, START_Y + particle.getY() * GAME_OBJECT_SIZE, GAME_OBJECT_SIZE, GAME_OBJECT_SIZE);
        }
    }
}
