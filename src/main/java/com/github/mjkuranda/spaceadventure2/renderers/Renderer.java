package com.github.mjkuranda.spaceadventure2.renderers;

import com.github.mjkuranda.spaceadventure2.GameData;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public abstract class Renderer {

    /** Game data */
    protected GameData data;

    /** Map mesh flag */
    protected boolean isMeshEnabled;

    public Renderer(GameData data, boolean isMeshEnabled) {
        this.data = data;
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

    /**
     * Returns game data reference
     * @return data GameData
     */
    public GameData getData() {
        return data;
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
