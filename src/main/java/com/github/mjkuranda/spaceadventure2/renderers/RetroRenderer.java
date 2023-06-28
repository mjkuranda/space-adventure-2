package com.github.mjkuranda.spaceadventure2.renderers;

import com.github.mjkuranda.spaceadventure2.GameData;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class RetroRenderer extends Renderer {

    public static final int RENDERER_WIDTH = 1280;
    public static final int RENDERER_HEIGHT = 1024;

    public static final int UNEXPLAINED_OFFSET = 7;

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
        renderPlayer(g);
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
