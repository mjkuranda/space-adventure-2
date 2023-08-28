package com.github.mjkuranda.spaceadventure2.resources;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.util.ResourceLoader;

import java.awt.*;
import java.io.IOException;

public class GameFont implements GameResource<UnicodeFont> {

    public static UnicodeFont PERFECT_DOS_VGA_437;
    public static UnicodeFont RETRO_GAMING;
    public static UnicodeFont VCR_OSD_MONO;
    public static UnicodeFont COMMODORE_64;

    private UnicodeFont uniFont;

    public GameFont(String name) throws SlickException, IOException, FontFormatException {
        this(name, 16.f);
    }

    public GameFont(String name, float size) throws IOException, FontFormatException, SlickException {
        Font uiFont1 = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT,
                ResourceLoader.getResourceAsStream(getResourcePath("fonts", name)));
        uiFont1 = uiFont1.deriveFont(Font.PLAIN, size);

        uniFont = new org.newdawn.slick.UnicodeFont(uiFont1);
        uniFont.addAsciiGlyphs();
        uniFont.getEffects().add(new ColorEffect(Color.white));
        uniFont.addAsciiGlyphs();
        uniFont.loadGlyphs();
    }

    public static void load() throws SlickException, IOException, FontFormatException {
        PERFECT_DOS_VGA_437 = new GameFont("Perfect DOS VGA 437.ttf").get();
        RETRO_GAMING = new GameFont("Retro Gaming.ttf").get();
        VCR_OSD_MONO = new GameFont("VCR_OSD_MONO_1.001.ttf").get();
        COMMODORE_64 = new GameFont("C64_Pro_Mono-STYLE.ttf").get();
    }

    @Override
    public UnicodeFont get() {
        return uniFont;
    }
}
