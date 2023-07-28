package com.github.mjkuranda.spaceadventure2.states.menus.options;

import com.github.mjkuranda.spaceadventure2.resources.GameFont;
import com.github.mjkuranda.spaceadventure2.states.menus.options.events.MenuOptionEvent;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;

public abstract class MenuOption implements MenuOptionEvent {

    protected static final int OPTION_WIDTH   = 200;
    protected static final int OPTION_HEIGHT  = 50;
    protected static final int OPTION_SPACE   = 100;

    protected String title;
    protected MenuOptionEvent onSelect;
    protected MenuOptionEvent onChange;

    public MenuOption(String title) {
        this.title = title;
    }

    public void render(GameContainer container, Graphics g, int idx, int currentOption, int length) {
        Vector2f coords = getCoords(container, idx, length);
        float x = coords.x;
        float y = coords.y;

        float textX = x + (float) OPTION_WIDTH / 2 - (float) GameFont.VCR_OSD_MONO.getWidth(title.equals("") ? "x" : title) / 2;
        float textY = y + (float) OPTION_HEIGHT / 2 - (float) GameFont.VCR_OSD_MONO.getHeight(title.equals("") ? "x" : title) / 2;

        g.setFont(GameFont.VCR_OSD_MONO);
        g.setColor(Color.white);
        g.drawRect(x, y, OPTION_WIDTH, OPTION_HEIGHT);
        g.drawString(title, textX, textY);

        if (currentOption == idx) {
            g.drawString(">", x - 20, textY);
            g.drawString("<", x + OPTION_WIDTH + 12, textY);
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

    @Override
    public void onChange(Input input) {
        if (onChange == null) {
            return;
        }

        onChange.onChange(input);
    }

    public MenuOption bindOnSelectEvent(MenuOptionEvent event) {
        onSelect = event;

        return this;
    }

    public MenuOption bindOnChangeEvent(MenuOptionEvent event) {
        onChange = event;

        return this;
    }

    protected Vector2f getCoords(GameContainer container, int idx, int length) {
        float containerSize = length * OPTION_HEIGHT + (length - 1) * OPTION_SPACE;

        float centerX = (float) (container.getWidth() / 2) - (float) (OPTION_WIDTH / 2);
        float centerY = (float) container.getHeight() / 2 - (float) (containerSize / 2);
        float x = centerX;
        float y = centerY + idx * OPTION_SPACE;

        return new Vector2f(x, y);
    }
}
