package com.github.mjkuranda.spaceadventure2.states.menus.options.events;

import com.github.mjkuranda.spaceadventure2.states.menus.options.values.StringMenuOptionValue;
import org.newdawn.slick.Input;

import java.util.HashMap;
import java.util.Map;

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
        value.update(value.get() + KeyNameFactory.get(input));
    }
}

class KeyNameFactory {

    private Map<Integer, Character> keyMapped = new HashMap<>() {{
        put(Input.KEY_1, '1');
        put(Input.KEY_2, '2');
        put(Input.KEY_3, '3');
        put(Input.KEY_4, '4');
        put(Input.KEY_5, '5');
        put(Input.KEY_6, '6');
        put(Input.KEY_7, '7');
        put(Input.KEY_8, '8');
        put(Input.KEY_9, '9');
        put(Input.KEY_0, '0');
        put(Input.KEY_NUMPAD0, '0');
        put(Input.KEY_NUMPAD1, '1');
        put(Input.KEY_NUMPAD2, '2');
        put(Input.KEY_NUMPAD3, '3');
        put(Input.KEY_NUMPAD4, '4');
        put(Input.KEY_NUMPAD5, '5');
        put(Input.KEY_NUMPAD6, '6');
        put(Input.KEY_NUMPAD7, '7');
        put(Input.KEY_NUMPAD8, '8');
        put(Input.KEY_NUMPAD9, '9');
        put(Input.KEY_Q, 'Q');
        put(Input.KEY_W, 'W');
        put(Input.KEY_E, 'E');
        put(Input.KEY_R, 'R');
        put(Input.KEY_T, 'T');
        put(Input.KEY_Y, 'Y');
        put(Input.KEY_U, 'U');
        put(Input.KEY_I, 'I');
        put(Input.KEY_O, 'O');
        put(Input.KEY_P, 'P');
        put(Input.KEY_A, 'A');
        put(Input.KEY_S, 'S');
        put(Input.KEY_D, 'D');
        put(Input.KEY_F, 'F');
        put(Input.KEY_G, 'G');
        put(Input.KEY_H, 'H');
        put(Input.KEY_J, 'J');
        put(Input.KEY_K, 'K');
        put(Input.KEY_L, 'L');
        put(Input.KEY_Z, 'Z');
        put(Input.KEY_X, 'X');
        put(Input.KEY_C, 'C');
        put(Input.KEY_V, 'V');
        put(Input.KEY_B, 'B');
        put(Input.KEY_N, 'N');
        put(Input.KEY_M, 'M');
    }};

    public static String get(Input input) {
        int keyCode = getKeyCode(input);

        if (keyCode == -1) {
            return "";
        }

        if (input.isKeyDown(Input.KEY_LSHIFT) || input.isKeyDown(Input.KEY_RSHIFT)) {
            return Input.getKeyName(keyCode);
        }

        return Input.getKeyName(keyCode).toLowerCase();
    }

    private static int getKeyCode(Input input) {
        if (input.isKeyPressed(Input.KEY_1)) {
            return Input.KEY_1;
        }
        // TODO: The other keys

        return -1;
    }
}