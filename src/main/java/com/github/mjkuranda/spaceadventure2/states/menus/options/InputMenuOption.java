package com.github.mjkuranda.spaceadventure2.states.menus.options;

import com.github.mjkuranda.spaceadventure2.resources.GameFont;
import com.github.mjkuranda.spaceadventure2.states.menus.options.values.StringMenuOptionValue;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public class InputMenuOption extends MenuOption {

    /** Current value reference */
    private StringMenuOptionValue value;

    /** Start time */
    private long startTime;

    public InputMenuOption(StringMenuOptionValue value) {
        super("");

        this.value = value;
        this.startTime = 0;
    }

    public void render(GameContainer container, Graphics g, int idx, int currentOption, int length) {
        super.render(container, g, idx, currentOption, length);

        if (startTime == 0) {
            startTime = System.currentTimeMillis();
        }

        Vector2f coords = getCoords(container, idx, length, 0, 0);
        float fontHeight = GameFont.VCR_OSD_MONO.getHeight("Lorem ipsum");
        float xOffset = (
                GameFont.VCR_OSD_MONO.getWidth("12340000123400001234000012") - GameFont.VCR_OSD_MONO.getWidth("1234000012340000")
        ) / 4.0f;
        float yOffset = (float) OPTION_HEIGHT / 2 - (float) fontHeight / 2;
        float textX = coords.x + xOffset;
        float textY = coords.y + yOffset;
        g.drawString(value.get(), textX, textY);
        g.drawString("Input your name", coords.x, coords.y - 1.5f * fontHeight);

        if (value.get().length() < StringMenuOptionValue.MAX_LENGTH && isSignRendering()) {
            float signWidth = GameFont.VCR_OSD_MONO.getWidth("X");
            float signHeight = GameFont.VCR_OSD_MONO.getHeight("X");
            float textOffset = GameFont.VCR_OSD_MONO.getWidth(value.get());

            g.setColor(Color.white);
            g.fillRect(textX + textOffset, textY, signWidth, signHeight);
        }
    }

    private boolean isSignRendering() {
        return (System.currentTimeMillis() - startTime) % 2000 > 1000;
    }

    public void reset() {
        this.value.update("");
        this.startTime = 0;
    }
}
