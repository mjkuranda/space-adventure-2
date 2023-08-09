package com.github.mjkuranda.spaceadventure2.renderers;

import com.github.mjkuranda.spaceadventure2.GameData;
import com.github.mjkuranda.spaceadventure2.PlayerData;
import com.github.mjkuranda.spaceadventure2.entities.Entity;
import com.github.mjkuranda.spaceadventure2.entities.EntityType;
import com.github.mjkuranda.spaceadventure2.entities.SpaceEntity;
import com.github.mjkuranda.spaceadventure2.entities.Spaceship;
import com.github.mjkuranda.spaceadventure2.resources.GameImage;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

import java.util.Random;

public class RetroRenderer extends Renderer {

    private static final int RENDERER_WIDTH = 1280;
    private static final int RENDERER_HEIGHT = 1024;

    private static final int MIDDLE_X = RENDERER_WIDTH / 2;
    private static final int MIDDLE_Y = RENDERER_HEIGHT / 2;

    private static final int UNEXPLAINED_OFFSET = 7;

    public RetroRenderer(GameData data) {
        super(data, true);
    }

    @Override
    protected void renderBackground(Graphics g) {
        float playerX = data.getPlayer().getX();
        int xOffset = (int) ((playerX - 8.0f) * 8);

        Image img = GameImage.BACKGROUND;
        float inc = PlayerData.getInstance().getDistance() / 2;
        img.draw(-xOffset - 64 - (inc / 2), 0 - (inc / 2), img.getWidth() + inc, img.getHeight() + inc);
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
        renderParticles();
        renderPlayer();
    }

    private void renderSpaceEntities(Graphics g) {
        g.setColor(Color.gray);

        for (var entity : data.getMappedEntities()) {
            renderSpaceEntity(g, data.getPlayer(), entity);
        }
    }

    private void renderPlayerMissiles(Graphics g) {
        g.setColor(Color.green);

        for (var missile : data.getPlayerMissiles()) {
            renderSpaceEntity(g, data.getPlayer(), missile, true);
        }
    }

    private void renderParticles() {
        for (var particle : data.getParticles()) {
            Vector2f[] coords = getCoordinatesToRender(particle, data.getPlayer());
            particle.getAnimation().draw(coords[0].x, coords[0].y, coords[1].x, coords[1].y);
        }
    }

    private void renderPlayer() {
        int playerWidth = 192;

        int xPlayer = RENDERER_WIDTH / 2 - (playerWidth / 2);
        int yPlayer = RENDERER_HEIGHT - 204;

        Random r = new Random();
        int vibration = Math.max(PlayerData.getInstance().getVibration(), 1);

        int xOffset = r.nextInt(vibration) - (vibration / 2);
        int yOffset = r.nextInt(vibration) - (vibration / 2);

        GameImage.SPACESHIP.draw(xPlayer + xOffset, yPlayer + yOffset, 0.37f);
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
        renderSpaceEntity(g, p, e, false);
    }

    private void renderSpaceEntity(Graphics g, Spaceship p, Entity e, boolean isMissile) {
        Vector2f[] coords = getCoordinatesToRender(e, p);

        float x = coords[0].x;
        float y = coords[0].y;
        float size = coords[1].x;

        if (isMissile) {
            g.fillOval(x, y, size, size);

            return;
        }

        Image img = e.getImage();
        img.draw(x, y, size, size);
    }

    private Vector2f[] getCoordinatesToRender(Entity e, SpaceEntity p) {
        // -0.5f because player is centered
        float x = e.getX() - p.getX() - 0.5f;
        int deltaX = (int) (x * 64);

        float yMapped = (float) (Math.pow(e.getY(), 2) + 9 * e.getY() - 2);
        float yPerc = yMapped / (440 + 44); // 18 -> weird
        float size = (yPerc * (e.getWidth() * 160)) + (e.getWidth() * 32);

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

        float weirdOffset = e.getType() == EntityType.PARTICLE && e.getWidth() < 1.0f ? -16 : 0;

        return new Vector2f[] {
                new Vector2f(x1 + xe - center + weirdOffset, ye - center),
                new Vector2f(size, size)
        };
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
