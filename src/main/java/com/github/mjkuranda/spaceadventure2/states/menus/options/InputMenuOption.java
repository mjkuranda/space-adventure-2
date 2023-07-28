package com.github.mjkuranda.spaceadventure2.states.menus.options;

import com.github.mjkuranda.spaceadventure2.resources.GameFont;
import com.github.mjkuranda.spaceadventure2.states.menus.options.values.StringMenuOptionValue;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public class InputMenuOption extends MenuOption {

    /** Current value reference */
    private StringMenuOptionValue value;

    public InputMenuOption(StringMenuOptionValue value) {
        super("");

        this.value = value;
    }

    public void render(GameContainer container, Graphics g, int idx, int currentOption, int length) {
        super.render(container, g, idx, currentOption, length);

        Vector2f coords = getCoords(container, idx, length);
        float fontHeight = GameFont.VCR_OSD_MONO.getHeight("Lorem ipsum");
        float xOffset = (
                GameFont.VCR_OSD_MONO.getWidth("12340000123400001234000012") - GameFont.VCR_OSD_MONO.getWidth("1234000012340000")
        ) / 4.0f;
        float yOffset = (float) OPTION_HEIGHT / 2 - (float) fontHeight / 2;
        g.drawString(value.get(), coords.x + xOffset, coords.y + yOffset);
        g.drawString("Input your name", coords.x, coords.y - 1.5f * fontHeight);
    }
}
