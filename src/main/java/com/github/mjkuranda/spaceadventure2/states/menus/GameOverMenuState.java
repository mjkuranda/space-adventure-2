package com.github.mjkuranda.spaceadventure2.states.menus;

import com.github.mjkuranda.spaceadventure2.PlayerData;
import com.github.mjkuranda.spaceadventure2.entities.EntityShapes;
import com.github.mjkuranda.spaceadventure2.entities.Particle;
import com.github.mjkuranda.spaceadventure2.resources.GameAnimation;
import com.github.mjkuranda.spaceadventure2.resources.GameFont;
import com.github.mjkuranda.spaceadventure2.resources.GameImage;
import com.github.mjkuranda.spaceadventure2.states.StatesId;
import com.github.mjkuranda.spaceadventure2.states.menus.options.MenuOption;
import com.github.mjkuranda.spaceadventure2.states.menus.options.SimpleMenuOption;
import com.github.mjkuranda.spaceadventure2.states.menus.options.events.EnterStateMenuOptionEvent;
import com.github.mjkuranda.spaceadventure2.states.menus.options.events.decorators.ResetPlayerStatisticsEvent;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class GameOverMenuState extends MenuState {

    private float approximation, speed = 4.0f;
    private boolean isApproximating = true;
    private byte currentBackgroundImageIndex = 0;
    private List<Particle> particleList;

    public GameOverMenuState(StateBasedGame game) {
        super();

        this.particleList = new LinkedList<>();

        this.bindOptions(new MenuOption[] {
                new SimpleMenuOption("Play again")
                        .bindOnSelectEvent(
                                new ResetPlayerStatisticsEvent(
                                        new EnterStateMenuOptionEvent(game, this, StatesId.GAME))),
                new SimpleMenuOption("Back to main menu")
                        .bindOnSelectEvent(
                                new ResetPlayerStatisticsEvent(
                                        new EnterStateMenuOptionEvent(game, this, StatesId.MAIN_MENU)))
        });
    }

    @Override
    public int getID() {
        return StatesId.GAME_OVER_MENU;
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        super.render(container, game, g);

        renderParticles();
    }

    @Override
    public void renderPartials(GameContainer container, Graphics g) {
        renderBackground(container, g);

        renderPartial(container, g, "GAME OVER", 200);
        renderPartial(container, g, PlayerData.getInstance().getName(), 256);
        renderPartial(container, g, "Your score: " + PlayerData.getInstance().getScore(), 280);
    }

    private void renderBackground(GameContainer container, Graphics g) {
        int xScale = (int) (approximation);
        int yScale = (int) (approximation);
        getBackgroundImage().draw(0 - (xScale / 2), 0 - (yScale / 2), 1920 + xScale, 1080 + yScale);
    }

    private void renderPartial(GameContainer container, Graphics g, String str, float y) {
        if (str == null) {
            return;
        }

        float x = (float) container.getWidth() / 2 - (float) GameFont.VCR_OSD_MONO.getWidth(str) / 2;
        g.drawString(str, x, y);
    }

    private void renderParticles() {
        for (var particle : particleList) {
            float x = particle.getX();
            float y = particle.getY();

            particle.getAnimation().draw(x, y, 64, 64);
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        super.update(container, game, delta);

        if (particleList.size() > 0 && particleList.get(0).getAnimation().isStopped()) {
            particleList.clear();
        }

        if(approximation >= -128 && approximation <= 1080) speed = 3.0f;
        if(approximation >= -200 && approximation <= -127) speed = 3.5f;
        if(approximation >= -300 && approximation <= -201) speed = 4.0f;
        if(approximation >= -400 && approximation <= -301) speed = 4.5f;
        if(approximation >= -650 && approximation <= -401) speed = 5.0f;
        if(approximation >= -800 && approximation <= -651) speed = 5.5f;
        if(approximation >= -1080 && approximation <= -901) speed = 6.0f;

        if(isApproximating) {
            approximation += speed;
        } else approximation -= speed;

        if(approximation >= 1080) isApproximating = false;

        else if(approximation <= -1080) {
            currentBackgroundImageIndex++;
            if(currentBackgroundImageIndex == 4) currentBackgroundImageIndex = 0;

            isApproximating = true;
        }
    }

    private Image getBackgroundImage() {
        return switch (currentBackgroundImageIndex) {
            case 0 -> GameImage.BACKGROUND_3;
            case 1 -> GameImage.BACKGROUND_4;
            case 2 -> GameImage.BACKGROUND_1;
            case 3 -> GameImage.BACKGROUND_2;
            default -> throw new IllegalStateException("Unexpected value: " + currentBackgroundImageIndex);
        };
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) {
        if (GameAnimation.EXPLOSION == null) {
            return;
        }

        Random r = new Random();

        for (int i = 0; i < 4800; i++) {
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
}
