package com.github.mjkuranda.spaceadventure2.states.menus.options.events;

import com.github.mjkuranda.spaceadventure2.resources.GameSound;
import com.github.mjkuranda.spaceadventure2.states.menus.options.values.StringMenuOptionValue;
import org.newdawn.slick.Input;
import org.newdawn.slick.Sound;

import java.util.Random;

public class InputMenuOptionEvent implements MenuOptionEvent {

    private StringMenuOptionValue value;

    public InputMenuOptionEvent(StringMenuOptionValue value) {
        this.value = value;
    }

    @Override
    public void onSelect() {}

    @Override
    public void onChange(int direction) {}

    @Override
    public void onChange(Input input) {
        if (input.isKeyPressed(Input.KEY_BACK)) {
            if (value.get().length() == 0) {
                return;
            }

            GameSound.KEY_BACKSPACE.play(1.0f, 0.5f);
            value.update(value.get().substring(0, value.get().length() - 1));

            return;
        }

        if (value.get().length() >= StringMenuOptionValue.MAX_LENGTH) {
            input.clearKeyPressedRecord();

            return;
        }

        value.update(value.get() + KeyNameFactory.get(input));
    }
}

class KeyNameFactory {

    public static String get(Input input) {
        int keyCode = getKeyCode(input);

        if (keyCode == -1) {
            return "";
        }

        if (keyCode == Input.KEY_SPACE) {
            GameSound.KEY_SPACE_BAR.play();
            
            return " ";
        }

        playKeySound();

        if (input.isKeyDown(Input.KEY_LSHIFT) || input.isKeyDown(Input.KEY_RSHIFT)) {
            return Input.getKeyName(keyCode);
        }

        return Input.getKeyName(keyCode).toLowerCase();
    }
    
    private static void playKeySound() {
        Sound s = switch (new Random().nextInt(4)) {
            case 0 -> GameSound.KEY_2;
            case 1 -> GameSound.KEY_3;
            case 2 -> GameSound.KEY_4;
            case 3 -> GameSound.KEY_W;
            default -> throw new IllegalStateException("Unexpected value: " + new Random().nextInt(4));
        };
        
        s.play();
    }

    private static int getKeyCode(Input input) {
        if (input.isKeyPressed(Input.KEY_1) || input.isKeyPressed(Input.KEY_NUMPAD1)) {
            return Input.KEY_1;
        }
        if (input.isKeyPressed(Input.KEY_2) || input.isKeyPressed(Input.KEY_NUMPAD2)) {
            return Input.KEY_2;
        }
        if (input.isKeyPressed(Input.KEY_3) || input.isKeyPressed(Input.KEY_NUMPAD3)) {
            return Input.KEY_3;
        }
        if (input.isKeyPressed(Input.KEY_4) || input.isKeyPressed(Input.KEY_NUMPAD4)) {
            return Input.KEY_4;
        }
        if (input.isKeyPressed(Input.KEY_5) || input.isKeyPressed(Input.KEY_NUMPAD5)) {
            return Input.KEY_5;
        }
        if (input.isKeyPressed(Input.KEY_6) || input.isKeyPressed(Input.KEY_NUMPAD6)) {
            return Input.KEY_6;
        }
        if (input.isKeyPressed(Input.KEY_7) || input.isKeyPressed(Input.KEY_NUMPAD7)) {
            return Input.KEY_7;
        }
        if (input.isKeyPressed(Input.KEY_8) || input.isKeyPressed(Input.KEY_NUMPAD8)) {
            return Input.KEY_8;
        }
        if (input.isKeyPressed(Input.KEY_9) || input.isKeyPressed(Input.KEY_NUMPAD9)) {
            return Input.KEY_9;
        }
        if (input.isKeyPressed(Input.KEY_0) || input.isKeyPressed(Input.KEY_NUMPAD0)) {
            return Input.KEY_0;
        }
        if (input.isKeyPressed(Input.KEY_Q)) {
            return Input.KEY_Q;
        }
        if (input.isKeyPressed(Input.KEY_W)) {
            return Input.KEY_W;
        }
        if (input.isKeyPressed(Input.KEY_E)) {
            return Input.KEY_E;
        }
        if (input.isKeyPressed(Input.KEY_R)) {
            return Input.KEY_R;
        }
        if (input.isKeyPressed(Input.KEY_T)) {
            return Input.KEY_T;
        }
        if (input.isKeyPressed(Input.KEY_Y)) {
            return Input.KEY_Y;
        }
        if (input.isKeyPressed(Input.KEY_U)) {
            return Input.KEY_U;
        }
        if (input.isKeyPressed(Input.KEY_I)) {
            return Input.KEY_I;
        }
        if (input.isKeyPressed(Input.KEY_O)) {
            return Input.KEY_O;
        }
        if (input.isKeyPressed(Input.KEY_P)) {
            return Input.KEY_P;
        }
        if (input.isKeyPressed(Input.KEY_A)) {
            return Input.KEY_A;
        }
        if (input.isKeyPressed(Input.KEY_S)) {
            return Input.KEY_S;
        }
        if (input.isKeyPressed(Input.KEY_D)) {
            return Input.KEY_D;
        }
        if (input.isKeyPressed(Input.KEY_F)) {
            return Input.KEY_F;
        }
        if (input.isKeyPressed(Input.KEY_G)) {
            return Input.KEY_G;
        }
        if (input.isKeyPressed(Input.KEY_H)) {
            return Input.KEY_H;
        }
        if (input.isKeyPressed(Input.KEY_J)) {
            return Input.KEY_J;
        }
        if (input.isKeyPressed(Input.KEY_J)) {
            return Input.KEY_J;
        }
        if (input.isKeyPressed(Input.KEY_K)) {
            return Input.KEY_K;
        }
        if (input.isKeyPressed(Input.KEY_L)) {
            return Input.KEY_L;
        }
        if (input.isKeyPressed(Input.KEY_Z)) {
            return Input.KEY_Z;
        }
        if (input.isKeyPressed(Input.KEY_X)) {
            return Input.KEY_X;
        }
        if (input.isKeyPressed(Input.KEY_C)) {
            return Input.KEY_C;
        }
        if (input.isKeyPressed(Input.KEY_V)) {
            return Input.KEY_V;
        }
        if (input.isKeyPressed(Input.KEY_B)) {
            return Input.KEY_B;
        }
        if (input.isKeyPressed(Input.KEY_N)) {
            return Input.KEY_N;
        }
        if (input.isKeyPressed(Input.KEY_M)) {
            return Input.KEY_M;
        }
        if (input.isKeyPressed(Input.KEY_SPACE)) {
            return Input.KEY_SPACE;
        }

        return -1;
    }
}