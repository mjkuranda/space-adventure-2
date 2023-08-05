package com.github.mjkuranda.spaceadventure2.states.highscore;

import com.github.mjkuranda.spaceadventure2.resources.GameFont;
import com.github.mjkuranda.spaceadventure2.states.StatesId;
import com.github.mjkuranda.spaceadventure2.states.menus.MenuState;
import com.github.mjkuranda.spaceadventure2.states.menus.options.MenuOption;
import com.github.mjkuranda.spaceadventure2.states.menus.options.SimpleMenuOption;
import com.github.mjkuranda.spaceadventure2.states.menus.options.events.ExitGameMenuOptionEvent;
import com.github.mjkuranda.spaceadventure2.states.menus.options.events.ResetHighScoreMenuOptionEvent;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class HighScoreState extends MenuState {

    private static final int RECORD_WIDTH = 512;
    private static final int RECORD_HEIGHT = 64;

    private int tableX;

    private int tableY;

    public HighScoreState(StateBasedGame game) {
        super();

        this.bindOptions(new MenuOption[] {
                new SimpleMenuOption("Reset high scores")
                        .bindOnSelectEvent(new ResetHighScoreMenuOptionEvent()),
                new SimpleMenuOption("Back to the menu")
//                        .bindOnSelectEvent(new EnterStateMenuOptionEvent(game, this, StatesId.MAIN_MENU))
                        .bindOnSelectEvent(new ExitGameMenuOptionEvent())
        });

        this.tableX = 0;
        this.tableY = 0;
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

    private void renderRecord(Graphics g, int line, String playerName, String score, String date) {
        g.drawRect(tableX, tableY + line * RECORD_HEIGHT, RECORD_WIDTH, RECORD_HEIGHT);
        g.drawLine(tableX + 192,  tableY + line * RECORD_HEIGHT, tableX + 192, tableY + line * RECORD_HEIGHT + RECORD_HEIGHT);
        g.drawLine(tableX + 384, tableY + line * RECORD_HEIGHT, tableX + 384, tableY + line * RECORD_HEIGHT + RECORD_HEIGHT);

        renderStringContent(g, playerName, tableX, tableY + line * RECORD_HEIGHT, 192);
        renderStringContent(g, score, tableX + 192, tableY + line * RECORD_HEIGHT, 192);
        renderStringContent(g, date, tableX + 384, tableY + line * RECORD_HEIGHT, 128);
    }

    private void renderRecord(Graphics g, int line, HighScoreRecord record) {
        if (record == null) {
            renderRecord(g, line, "", "", "");

            return;
        }

        renderRecord(g, line, record.getPlayerName(), record.getScore() + "", record.getDate());
    }

    private void renderStringContent(Graphics g, String s, float x, float y, int width) {
        int sWidth = GameFont.VCR_OSD_MONO.getWidth(s);
        int sHeight = GameFont.VCR_OSD_MONO.getHeight(s);

        g.drawString(s, x + (float) (width - sWidth) / 2, y + (float) (RECORD_HEIGHT - sHeight) / 2);
    }
}
