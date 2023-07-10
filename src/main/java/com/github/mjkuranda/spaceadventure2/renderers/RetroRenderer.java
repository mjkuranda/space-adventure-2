package com.github.mjkuranda.spaceadventure2.renderers;

import com.github.mjkuranda.spaceadventure2.GameData;
import com.github.mjkuranda.spaceadventure2.entities.Entity;
import com.github.mjkuranda.spaceadventure2.entities.EntityType;
import com.github.mjkuranda.spaceadventure2.entities.Spaceship;
import com.github.mjkuranda.spaceadventure2.entities.missiles.Missile;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class RetroRenderer extends Renderer {

    private static final int RENDERER_WIDTH = 1280;
    private static final int RENDERER_HEIGHT = 1024;

    private static final int MIDDLE_X = RENDERER_WIDTH  /2;
    private static final int MIDDLE_Y = RENDERER_HEIGHT  /2;

    private static final int UNEXPLAINED_OFFSET = 7;

    public RetroRenderer(GameData data) {
        super(data, true);
    }

    @Override
    protected void renderBackground(Graphics g) {

    }

    @Override
    protected void renderMesh(Graphics g) {
        g.setColor(Color.red);
        g.drawLine(0, RENDERER_HEIGHT / 2, RENDERER_WIDTH, RENDERER_HEIGHT / 2);

        for (int i = -1; i < 16; i++) {
            drawColumn(g, i - data.getPlayer().getX());
        }

        for (int i = 0; i < 16; i++) {
            drawRows(g, i);
        }
    }

    @Override
    protected void renderEntities(Graphics g) {
        renderSpaceEntities(g);
        renderPlayerMissiles(g);
        renderPlayer(g);
    }

    private void renderSpaceEntities(Graphics g) {
        g.setColor(Color.gray);

        for (var line : data.getSpaceEntityList()) {
            for (var entity : line) {
                renderSpaceEntity(g, data.getPlayer(), entity);
            }
        }
    }

    private void renderPlayerMissiles(Graphics g) {
        g.setColor(Color.green);

        for (var missile : data.getPlayerMissiles()) {
//            renderMissile(g, missile);
            renderSpaceEntity(g, data.getPlayer(), missile);
        }
    }

    private void renderPlayer(Graphics g) {
        int xPlayer = RENDERER_WIDTH / 2 - 64;
        int yPlayer = RENDERER_HEIGHT - 218;

        g.setColor(Color.yellow);
        g.fillRect(xPlayer, yPlayer, 128, 64);
    }

    private void drawColumn(Graphics g, float x) {
        int xStart = RENDERER_WIDTH / 2;
        int yStart = RENDERER_HEIGHT / 2;
        int yEnd = RENDERER_HEIGHT;

        int deltaX = (int) (x * 64);

        g.drawLine(xStart + x * 32 + UNEXPLAINED_OFFSET, yStart, xStart + 128 + deltaX * 4 + UNEXPLAINED_OFFSET, yEnd);
    }

    private void drawRows(Graphics g, int idx) {
        int xStart = 0;
        int yStart = RENDERER_HEIGHT / 2;
        int xEnd = RENDERER_WIDTH;

        int deltaY = getYDelta(idx);
        int offsetY = getYOffset(idx);

        int yLine = yStart + deltaY + offsetY;

        g.drawLine(xStart, yLine, xEnd, yLine);
    }

    private void renderSpaceEntity(Graphics g, Spaceship p, Entity e) {
        // -0.5f because player is centered
        float x = e.getX() - p.getX() - 0.5f;
        int deltaX = (int) (x * 64);

        float yMapped = (float) (Math.pow(e.getY(), 2) + 9 * e.getY() - 2);
        float yPerc = yMapped / (440 + 44); // 18 -> weird
        float size = (yPerc * 58) + (e.getWidth() * 16);

        // Line
        float x1 = MIDDLE_X + UNEXPLAINED_OFFSET + x * 32;
        float y1 = MIDDLE_Y;
        float x2 = MIDDLE_X + UNEXPLAINED_OFFSET + 128 + deltaX * 4;
        float y2 = RENDERER_HEIGHT;

        float a = (y2 - y1) / (x2 - x1);
        float b = y1 - a * x1;
        float xe = (x2 - x1) * yPerc;
        float ye = a * (x1 + xe) + b;

        float center = size / 2;

        if (e.getType() == EntityType.LASER_MISSILE) {
            g.fillOval(x1 + xe - center, ye - center, size, size);
        } else g.fillRect(x1 + xe - center, ye - center, size, size);
        // Render line
        // g.drawLine(x1, y1, x2, y2);
    }

    private void renderMissile(Graphics g, Entity m) {

    }

    private int getYDelta(int idx) {
        return 2 * idx + 8;
    }

    private int getYOffset(int idx) {
        if (idx == 0) {
            return 0;
        }

        int offset = getYDelta(idx);

        return getYOffset(idx - 1) + offset;
    }
}
