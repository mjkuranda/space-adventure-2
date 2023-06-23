package com.github.mjkuranda.spaceadventure2.renderers;

import com.github.mjkuranda.spaceadventure2.map.GameMap;
import org.newdawn.slick.Graphics;

public abstract class Renderer {

    /** Map */
    private GameMap map;

    /** Map mesh flag */
    private boolean isMeshEnabled;

    public Renderer(GameMap map, boolean isMeshEnabled) {
        this.map = map;
        this.isMeshEnabled = isMeshEnabled;
    }

    /** Renders screen */
    public void render(Graphics g) {
        renderBackground(g);

        if (isMeshEnabled) {
            renderMesh(g);
        }

        renderEntities(g);
    }

    /** Toggles map mesh */
    public void toggleMesh() {
        isMeshEnabled = !isMeshEnabled;
    }

    /**
     * Returns flag if mesh is enabled or not.
     * @return boolean
     */
    public boolean isMeshEnabled() {
        return isMeshEnabled;
    }

    /**
     * Renders map background, e. g. universe image.
     * @param g Graphics
     */
    protected abstract void renderBackground(Graphics g);

    /**
     * Renders map mesh, e. g. set of red lines, defining map fields.
     * @param g Graphics
     */
    protected abstract void renderMesh(Graphics g);

    /**
     * Renders all entities on the map.
     * @param g Graphics
     */
    protected abstract void renderEntities(Graphics g);
}
