package com.github.mjkuranda.spaceadventure2.states.menus;

import com.github.mjkuranda.spaceadventure2.resources.GameSound;
import com.github.mjkuranda.spaceadventure2.states.menus.options.MenuOption;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public abstract class MenuState extends BasicGameState {

    protected MenuOption[] options;

    protected int currentOption;

    public MenuState() {
        this(null);
    }

    public MenuState(MenuOption[] options) {
        this.options = options;
        this.currentOption = 0;
    }

    public MenuState bindOptions(MenuOption[] options) {
        this.options = options;

        return this;
    }

    public void reset() {
        for (var option : options) {
            option.reset();
        }

        currentOption = 0;
    }

    @Override
    public abstract int getID();

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {}

    public abstract void renderPartials(GameContainer container, Graphics g);

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        renderPartials(container, g);

        for (int i = 0; i < options.length; i++) {
            options[i].render(container, g, i, currentOption, options.length);
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        Input in = container.getInput();

        if (in.isKeyPressed(Input.KEY_ENTER)) {
            GameSound.CONFIRM.play();
            options[currentOption].onSelect();
        }

        if (in.isKeyPressed(Input.KEY_UP)) {
            GameSound.BEEP.play();
            currentOption--;

            if (currentOption < 0) {
                currentOption = options.length - 1;
            }
        }

        if (in.isKeyPressed(Input.KEY_DOWN)) {
            GameSound.BEEP.play();
            currentOption++;

            if (currentOption >= options.length) {
                currentOption = 0;
            }
        }

        /** Value handling */
        if (in.isKeyPressed(Input.KEY_LEFT)) {
            options[currentOption].onChange(-1);
            GameSound.BEEP.play();
        } else
        if (in.isKeyPressed(Input.KEY_RIGHT)) {
            options[currentOption].onChange(1);
            GameSound.BEEP.play();
        } else options[currentOption].onChange(in);
    }
}
