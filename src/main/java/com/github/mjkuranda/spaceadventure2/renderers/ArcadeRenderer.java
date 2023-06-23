package com.github.mjkuranda.spaceadventure2.renderers;

import com.github.mjkuranda.spaceadventure2.map.GameMap;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class ArcadeRenderer extends Renderer {

    public ArcadeRenderer(GameMap map) {
        super(map, true);
    }

    @Override
    protected void renderBackground(Graphics g) {
        //
    }

    @Override
    protected void renderMesh(Graphics g) {
        for (int y = 0; y < GameMap.Y_SIZE; y++) {
            for (int x = 0; x < GameMap.X_SIZE; x++) {
                g.setColor(Color.red);
                g.drawRect(x * 32, y * 32, 32, 32);
            }
        }
    }

    @Override
    protected void renderEntities(Graphics g) {
        for (var line : getMap().getSpaceEntityList()) {
            var it = line.iterator();

            while (it.hasNext()) {
                var entity = it.next();

                g.setColor(Color.gray);
                g.fillOval(entity.getX() * 32, entity.getY() * 32, 32, 32);
            }
        }
    }
}
