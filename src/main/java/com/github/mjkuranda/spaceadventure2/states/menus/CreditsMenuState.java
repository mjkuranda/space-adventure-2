package com.github.mjkuranda.spaceadventure2.states.menus;

import com.github.mjkuranda.spaceadventure2.resources.GameFont;
import com.github.mjkuranda.spaceadventure2.states.StatesId;
import com.github.mjkuranda.spaceadventure2.states.menus.options.MenuOption;
import com.github.mjkuranda.spaceadventure2.states.menus.options.SimpleMenuOption;
import com.github.mjkuranda.spaceadventure2.states.menus.options.events.EnterStateMenuOptionEvent;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.util.LinkedList;
import java.util.List;

public class CreditsMenuState extends MenuState {

    private static int GAME_WIDTH;

    private int optionX, optionY;

    /** Text offset */
    private float offset;

    /** Credit section list */
    private List<CreditSection> creditSections;

    public CreditsMenuState(StateBasedGame game) {
        super();

        this.bindOptions(new MenuOption[] {
                new SimpleMenuOption("Back to the menu")
                        .bindOnSelectEvent(new EnterStateMenuOptionEvent(game, this, StatesId.MAIN_MENU))
        });

        this.optionX = game.getContainer().getWidth() / 2 - MenuOption.OPTION_WIDTH / 2;
        this.optionY = game.getContainer().getHeight() - 100;

        GAME_WIDTH = game.getContainer().getWidth();

        initCredits();
    }

    private void initCredits() {
        this.creditSections = new LinkedList<>();
        this.creditSections.add(
                new CreditSection("Creator")
                        .addCredit(new CreditItem("Marek Kuranda"))
        );
        this.creditSections.add(
                new CreditSection("Music")
                        .addCredit(new CreditItem("Arcade by Evgeny Bardyuzha"))
        );
        this.creditSections.add(
                new CreditSection("Sounds")
                        .addCredit(new CreditItem("8-bit explosion low resonant by s9menine"))
                        .addCredit(new CreditItem("explosion_asteroid by runningmind"))
                        .addCredit(new CreditItem("explosion_asteroid2 by runningmind"))
                        .addCredit(new CreditItem("explosion_enemy by runningmind"))
                        .addCredit(new CreditItem("one_beep by Kenneth_Cooney"))
                        .addCredit(new CreditItem("Computer backspace by 14FPanskaVesecka_Karolina"))
                        .addCredit(new CreditItem("wKey by UberBosser"))
                        .addCredit(new CreditItem("spacebar by Krokulator"))
                        .addCredit(new CreditItem("Keyboard Key with Pitch and Phaser Effects by AntoineRomo"))
                        .addCredit(new CreditItem("Compute Key by milo03"))
                        .addCredit(new CreditItem("Button Pressed by Jofae"))
        );
        this.creditSections.add(
                new CreditSection("Images")
                        .addCredit(new CreditItem("background-1 by FelixMittermeier from Pixabay"))
                        .addCredit(new CreditItem("background-2 by Gerd Altmann from Pixabay"))
                        .addCredit(new CreditItem("background-3 by Lumina Obscura from Pixabay"))
                        .addCredit(new CreditItem("background-4 by Pexels from Pixabay"))
        );
        this.creditSections.add(
                new CreditSection("Dedicating this game to my Dad")
                        .addCredit(new CreditItem("for motivating me to finish this game and thanking him for his support :)"))
        );
        this.creditSections.add(new CreditSection("Created in 2023"));
    }

    @Override
    public int getID() {
        return StatesId.CREDITS;
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        renderPartials(container, g);

        g.setColor(Color.black);
        g.fillRect(0, container.getHeight() - 256, GAME_WIDTH, 256);
        g.setColor(Color.white);
        g.setFont(GameFont.VCR_OSD_MONO);

        for (int i = 0; i < options.length; i++) {
            options[i].render(container, g, i, currentOption, options.length, optionX, optionY);
        }
    }

    @Override
    public void renderPartials(GameContainer container, Graphics g) {
        for (int s = 0; s < creditSections.size(); s++) {
            var section = creditSections.get(s);
            int titleWidth = GameFont.VCR_OSD_MONO.getWidth(section.getTitle());
            g.drawString(section.getTitle(), container.getWidth() / 2 - titleWidth / 2, container.getHeight() - 256 + s * 256 - offset);

            for (int c = 0; c < section.getCredits().size(); c++) {
                var credit = section.getCredits().get(c);
                int creditWidth = GameFont.VCR_OSD_MONO.getWidth(credit.getText());
                g.drawString(credit.getText(), container.getWidth() / 2 - creditWidth / 2, container.getHeight() - 256 + s * 256 - offset + c * 16 + 38);

                // For ń :)
                if (s == 0) {
                    g.fillRect(container.getWidth() / 2 - creditWidth / 2 + 94, container.getHeight() - 256 + s * 256 - offset + c * 16 + 38 + 2, 2, 4);
                }
            }
        }

        offset++;
    }

    @Override
    public void reset() {
        super.reset();

        offset = 0;
    }

    class CreditSection {
        private String title;

        private List<CreditItem> credits;

        public CreditSection(String title) {
            this.title = title;
            this.credits = new LinkedList<>();
        }

        public CreditSection addCredit(CreditItem credit) {
            credits.add(credit);

            return this;
        }

        public List<CreditItem> getCredits() {
            return credits;
        }

        public String getTitle() {
            return title;
        }
    }

    class CreditItem {
        private String text;

        public CreditItem(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }
}
