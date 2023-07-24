package com.github.mjkuranda.spaceadventure2.states.menus.options;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

interface MenuOptionEvent {
    /***
     * Executes a specific task for menu option
     */
    void onSelect();

    /***
     * Changes current value
     * @param direction -1 left, 1 right
     */
    void onChange(int direction);
}

public abstract class MenuOption implements MenuOptionEvent {

    protected String title;

    public MenuOption(String title) {
        this.title = title;
    }

    public void render(GameContainer container, Graphics g, int idx, int length) {
        float centerX = (float) container.getWidth() / 2;
        float centerY = (float) container.getHeight() / 2;
        float x = centerX - 100;
        float y = centerY + idx * 100;

        g.setColor(Color.white);
        g.drawRect(x, y, 200, 50);
        g.drawString(title, x, y);
    }

    @Override
    public abstract void onSelect();

    @Override
    public abstract void onChange(int direction);
}
