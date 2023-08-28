package com.github.mjkuranda.spaceadventure2.states.menus;

import com.github.mjkuranda.spaceadventure2.SpaceAdventure2;
import com.github.mjkuranda.spaceadventure2.entities.EntityShapes;
import com.github.mjkuranda.spaceadventure2.entities.Particle;
import com.github.mjkuranda.spaceadventure2.resources.GameAnimation;
import com.github.mjkuranda.spaceadventure2.resources.GameFont;
import com.github.mjkuranda.spaceadventure2.resources.GameImage;
import com.github.mjkuranda.spaceadventure2.states.StatesId;
import com.github.mjkuranda.spaceadventure2.states.menus.options.MenuOption;
import com.github.mjkuranda.spaceadventure2.states.menus.options.SimpleMenuOption;
import com.github.mjkuranda.spaceadventure2.states.menus.options.events.EnterStateMenuOptionEvent;
import com.github.mjkuranda.spaceadventure2.states.menus.options.events.ExitGameMenuOptionEvent;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MainMenuState extends MenuState {

    private int optionX, optionY;

    /** Particle list for explosions */
    private List<Particle> particleList;

    private BlindingLabel navigationInfoLabel;

    public MainMenuState(StateBasedGame game) {
        super();

        this.optionX = game.getContainer().getWidth() / 2 - MenuOption.OPTION_WIDTH / 2;
        this.optionY = game.getContainer().getHeight() / 2 - 250;

        this.particleList = new LinkedList<>();

        this.navigationInfoLabel = new BlindingLabel("Press up or down arrow to navigate and ENTER to select");

        this.bindOptions(new MenuOption[] {
                new SimpleMenuOption("Start")
                        .bindOnSelectEvent(new EnterStateMenuOptionEvent(game, this, StatesId.NEW_GAME_MENU)),
                new SimpleMenuOption("How to play")
                        .bindOnSelectEvent(new EnterStateMenuOptionEvent(game, this, StatesId.HOW_TO_PLAY)),
                new SimpleMenuOption("High Score")
                        .bindOnSelectEvent(new EnterStateMenuOptionEvent(game, this, StatesId.HIGH_SCORE)),
                new SimpleMenuOption("Credits")
                        .bindOnSelectEvent(new EnterStateMenuOptionEvent(game, this, StatesId.CREDITS)),
                new SimpleMenuOption("Exit")
                        .bindOnSelectEvent(new ExitGameMenuOptionEvent())
        });
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) {
        if (GameAnimation.EXPLOSION == null) {
            return;
        }

        Random r = new Random();

        for (int i = 0; i < 6400; i++) {
            float x = r.nextFloat() * (container.getWidth() + 256) - 128;
            float y = r.nextFloat() * (container.getHeight() + 256) - 128;
            Shape shape = switch (r.nextInt(3)) {
                case 0 -> EntityShapes.SMALL_EXPLOSION_SHAPE;
                case 1 -> EntityShapes.MEDIUM_EXPLOSION_SHAPE;
                case 2 -> EntityShapes.LARGE_EXPLOSION_SHAPE;
                default -> throw new IllegalStateException("Unexpected value: " + r.nextInt(3));
            };

            particleList.add(new Particle(GameAnimation.EXPLOSION, x, y, shape));
        }
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        renderPartials(container, g);

        for (int i = 0; i < options.length; i++) {
            options[i].render(container, g, i, currentOption, options.length, optionX, optionY);
        }

        renderParticles();
    }

    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        super.update(container, game, delta);

        if (particleList.size() > 0 && particleList.get(0).getAnimation().isStopped()) {
            particleList.clear();
        }
    }

    @Override
    public int getID() {
        return StatesId.MAIN_MENU;
    }

    @Override
    public void renderPartials(GameContainer container, Graphics g) {
        g.setFont(GameFont.VCR_OSD_MONO);
        float fontHeight = GameFont.VCR_OSD_MONO.getHeight("X");

        String version = SpaceAdventure2.GAME_VERSION;
        int versionWidth = getStringWidth(version, GameFont.VCR_OSD_MONO);
        float versionX = (float) container.getWidth() - versionWidth;
        float versionY = (float) container.getHeight() - fontHeight - fontHeight;

        String creator = "Created by: " + SpaceAdventure2.GAME_CREATOR;
        int creatorWidth = getStringWidth(creator, GameFont.VCR_OSD_MONO);
        float creatorX = (float) container.getWidth() - creatorWidth;
        float creatorY = (float) container.getHeight() - fontHeight;

        // Game texts
        GameImage.LOGO.draw(
                container.getWidth() / 2.f - 512 / 2.f,
                48
        );
        g.drawString(version, versionX, versionY);
        g.drawString(creator, creatorX, creatorY);
        g.fillRect(container.getWidth() - 24, creatorY, 2, 4);

        // Navigation text
        float navX = container.getWidth() / 2 - navigationInfoLabel.getTextWidth() / 2;
        navigationInfoLabel.render(g, navX, container.getHeight() - 128);
    }

    private void renderParticles() {
        for (var particle : particleList) {
            float x = particle.getX();
            float y = particle.getY();

            particle.getAnimation().draw(x, y, 64, 64);
        }
    }

    private int getStringWidth(String str, UnicodeFont font) {
        return font.getWidth(str);
    }
}

class BlindingLabel {

    private String text;

    public BlindingLabel(String text) {
        this.text = text;
    }

    public void render(Graphics g, float x, float y) {
        long rest = Math.abs(500 - (System.currentTimeMillis() % 1000));
        float intensity = (float) rest / 500.0f;
        int c = (int) (255 * intensity);

        g.setColor(new Color(c, c, c));
        g.drawString(text, x, y);
    }

    public int getTextWidth() {
        return GameFont.VCR_OSD_MONO.getWidth(text);
    }
}