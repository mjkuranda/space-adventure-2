package com.github.mjkuranda.spaceadventure2.states.highscore;

import com.github.mjkuranda.spaceadventure2.resources.GameFont;
import com.github.mjkuranda.spaceadventure2.states.StatesId;
import com.github.mjkuranda.spaceadventure2.states.menus.MenuState;
import com.github.mjkuranda.spaceadventure2.states.menus.options.MenuOption;
import com.github.mjkuranda.spaceadventure2.states.menus.options.SimpleMenuOption;
import com.github.mjkuranda.spaceadventure2.states.menus.options.events.EnterStateMenuOptionEvent;
import com.github.mjkuranda.spaceadventure2.states.menus.options.events.ResetHighScoreMenuOptionEvent;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class HighScoreState extends MenuState {

    private static final int RECORD_WIDTH = 512;
    private static final int RECORD_HEIGHT = 64;

    private float tableX;

    private float tableY;

    private float optionX;

    private float optionY;

    public HighScoreState(StateBasedGame game) {
        super();

        this.bindOptions(new MenuOption[] {
                new SimpleMenuOption("Reset high scores")
                        .bindOnSelectEvent(new ResetHighScoreMenuOptionEvent()),
                new SimpleMenuOption("Back to the menu")
                        .bindOnSelectEvent(new EnterStateMenuOptionEvent(game, this, StatesId.MAIN_MENU))
        });

        this.tableX = game.getContainer().getWidth() / 2.0f - RECORD_WIDTH / 2.0f;
        this.tableY = game.getContainer().getHeight() / 2.0f - (RECORD_HEIGHT * (HighScoreHandler.MAX_RECORDS_COUNT + 1)) / 2.0f - 128;

        this.optionX = game.getContainer().getWidth() / 2.0f - MenuOption.OPTION_WIDTH / 2.0f;
        this.optionY = tableY + (RECORD_HEIGHT * (HighScoreHandler.MAX_RECORDS_COUNT + 1)) + 64;
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        renderPartials(container, g);

        for (int i = 0; i < options.length; i++) {
            options[i].render(container, g, i, currentOption, options.length, optionX, optionY);
        }
    }

    @Override
    public int getID() {
        return StatesId.HIGH_SCORE;
    }

    @Override
    public void renderPartials(GameContainer container, Graphics g) {
        HighScoreHandler handler = HighScoreHandler.getInstance();
        var records = handler.getRecords();
        g.setFont(GameFont.VCR_OSD_MONO);

        renderRecord(g, 0, "Player", "Score", "Date");

        for (int i = 0; i < HighScoreHandler.MAX_RECORDS_COUNT; i++) {
            HighScoreRecord record = records.size() > i ? records.get(i) : null;
            renderRecord(g, i + 1, record);
        }
    }

    private void renderRecord(Graphics g, int line, HighScoreRecord record) {
        if (record == null) {
            renderRecord(g, line, "", "", "");

            return;
        }

        renderRecord(g, line, record.getPlayerName(), record.getScore() + "", record.getDate());
    }

    private void renderRecord(Graphics g, int line, String playerName, String score, String date) {
        renderRecordBackground(g, line, tableX, tableY + line * RECORD_HEIGHT);

        g.setColor(Color.white);
        g.drawRect(tableX, tableY + line * RECORD_HEIGHT, RECORD_WIDTH, RECORD_HEIGHT);
        g.drawLine(tableX + 96,  tableY + line * RECORD_HEIGHT, tableX + 96, tableY + line * RECORD_HEIGHT + RECORD_HEIGHT);
        g.drawLine(tableX + 288,  tableY + line * RECORD_HEIGHT, tableX + 288, tableY + line * RECORD_HEIGHT + RECORD_HEIGHT);
        g.drawLine(tableX + 384, tableY + line * RECORD_HEIGHT, tableX + 384, tableY + line * RECORD_HEIGHT + RECORD_HEIGHT);

        renderStringContent(g, line == 0 ? "Position" : line + "", tableX, tableY + line * RECORD_HEIGHT, 96);
        renderStringContent(g, playerName, tableX + 96, tableY + line * RECORD_HEIGHT, 192);
        renderStringContent(g, score, tableX + 288, tableY + line * RECORD_HEIGHT, 96);
        renderStringContent(g, date, tableX + 384, tableY + line * RECORD_HEIGHT, 128);
    }

    private void renderRecordBackground(Graphics g, int line, float x, float y) {
        Color color = switch (line) {
            case 0 -> Color.darkGray;
            case 1 -> Color.orange;
            case 2 -> Color.lightGray;
            case 3 -> new Color(210,105,30);
            default -> Color.gray;
        };

        g.setColor(color);
        g.fillRect(x, y, RECORD_WIDTH, RECORD_HEIGHT);
    }

    private void renderStringContent(Graphics g, String s, float x, float y, int width) {
        int sWidth = GameFont.VCR_OSD_MONO.getWidth(s);
        int sHeight = GameFont.VCR_OSD_MONO.getHeight(s);

        g.drawString(s, x + (float) (width - sWidth) / 2, y + (float) (RECORD_HEIGHT - sHeight) / 2);
    }
}
