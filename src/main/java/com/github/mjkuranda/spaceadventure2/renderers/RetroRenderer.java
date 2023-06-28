package com.github.mjkuranda.spaceadventure2.renderers;

import com.github.mjkuranda.spaceadventure2.GameData;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class RetroRenderer extends Renderer {

    public static final int RENDERER_WIDTH = 1280;
    public static final int RENDERER_HEIGHT = 1024;

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

        for (int i = 0; i < 8; i++) {
            drawColumn(g, i);
        }

        for (int i = 0; i < 16; i++) {
            drawRows(g, i);
        }
    }

    @Override
    protected void renderEntities(Graphics g) {

    }

    private void drawColumn(Graphics g, int idx) {
        int xStart = RENDERER_WIDTH / 2;
        int yStart = RENDERER_HEIGHT / 2;
        int yEnd = RENDERER_HEIGHT;

        if (idx == 0) {
            g.drawLine(xStart - 32, yStart, xStart - 32 - 128, yEnd);
            g.drawLine(xStart + 32, yStart, xStart + 32 + 128, yEnd);

            return;
        }

        int deltaX = 64 * idx;

        g.drawLine(xStart - 32 - deltaX, yStart, xStart - 32 - 128 - deltaX * 4, yEnd);
        g.drawLine(xStart + 32 + deltaX, yStart, xStart + 32 + 128 + deltaX * 4, yEnd);
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
