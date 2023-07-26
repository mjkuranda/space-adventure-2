package com.github.mjkuranda.spaceadventure2.resources;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.util.ResourceLoader;

import java.awt.*;
import java.io.IOException;

public class GameFont implements GameResource<UnicodeFont> {

    public static GameFont PERFECT_DOS_VGA_437;
    public static GameFont PERFECT_DOS_VGA_437_WIN;
    public static GameFont REAL_VHS;
    public static GameFont RETRO_GAMING;

    private UnicodeFont uniFont;

    public GameFont(String name) throws IOException, FontFormatException, SlickException {
        Font uiFont1 = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT,
                ResourceLoader.getResourceAsStream(getResourcePath("fonts", name)));
        uiFont1 = uiFont1.deriveFont(Font.PLAIN, 16.f); //You can change "PLAIN" to "BOLD" or "ITALIC"... and 16.f is the size of your font

        uniFont = new org.newdawn.slick.UnicodeFont(uiFont1);
        uniFont.addAsciiGlyphs();
        uniFont.getEffects().add(new ColorEffect(Color.white)); //You can change your color here, but you can also change it in the render{ ... }
        uniFont.addAsciiGlyphs();
        uniFont.loadGlyphs();

        // PREFIX_PATH + "fonts/yourFontFile.ttf"
    }

    public static void load() throws SlickException, IOException, FontFormatException {
        PERFECT_DOS_VGA_437 = new GameFont("Perfect DOS VGA 437.ttf");
        PERFECT_DOS_VGA_437_WIN = new GameFont("Perfect DOS VGA 437 Win.ttf");
        REAL_VHS = new GameFont("RealVhsFontRegular-WyV0z.ttf");
        RETRO_GAMING = new GameFont("Retro Gaming.ttf");
    }

    @Override
    public UnicodeFont get() {
        return uniFont;
    }
}
