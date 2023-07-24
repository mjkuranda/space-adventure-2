package com.github.mjkuranda.spaceadventure2.states.menus.options;

import com.github.mjkuranda.spaceadventure2.states.menus.options.events.MenuOptionEvent;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public abstract class MenuOption implements MenuOptionEvent {

    protected String title;
    protected MenuOptionEvent onSelect;
    protected MenuOptionEvent onChange;

    public MenuOption(String title) {
        this.title = title;
    }

    public void render(GameContainer container, Graphics g, int idx, int currentOption, int length) {
        float centerX = (float) container.getWidth() / 2;
        float centerY = (float) container.getHeight() / 2;
        float x = centerX - 100;
        float y = centerY + idx * 100;

        g.setColor(Color.white);
        g.drawRect(x, y, 200, 50);
        g.drawString(title, x, y);

        if (currentOption == idx) {
            g.drawString(">", x - 20, y);
            g.drawString("<", x + 220, y);
        }
    }

    @Override
    public void onSelect() {
        if (onSelect == null) {
            return;
        }

        onSelect.onSelect();
    }

    @Override
    public void onChange(int direction) {
        if (onChange == null) {
            return;
        }

        onChange.onChange(direction);
    }

    public MenuOption bindOnSelectEvent(MenuOptionEvent event) {
        onSelect = event;

        return this;
    }

    public MenuOption bindOnChangeEvent(MenuOptionEvent event) {
        onChange = event;

        return this;
    }
}
