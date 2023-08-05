package com.github.mjkuranda.spaceadventure2.states.highscore;

import com.github.mjkuranda.spaceadventure2.resources.GameFont;
import com.github.mjkuranda.spaceadventure2.states.StatesId;
import com.github.mjkuranda.spaceadventure2.states.menus.MenuState;
import com.github.mjkuranda.spaceadventure2.states.menus.options.MenuOption;
import com.github.mjkuranda.spaceadventure2.states.menus.options.SimpleMenuOption;
import com.github.mjkuranda.spaceadventure2.states.menus.options.events.ExitGameMenuOptionEvent;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class HighScoreState extends MenuState {

    private static final int RECORD_WIDTH = 512;
    private static final int RECORD_HEIGHT = 64;

    public HighScoreState(StateBasedGame game) {
        super();

        this.bindOptions(new MenuOption[] {
                new SimpleMenuOption("Back to the menu")
//                        .bindOnSelectEvent(new EnterStateMenuOptionEvent(game, this, StatesId.MAIN_MENU))
                        .bindOnSelectEvent(new ExitGameMenuOptionEvent())
        });
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

        int tableX = 0;
        int tableY = 0;

        renderRecordHeader(g, tableX, tableY);

        for (int i = 0; i < HighScoreHandler.MAX_RECORDS_COUNT; i++) {
            renderRecord(g, tableX, tableY, records.get(i), i);
        }

        // Draw lines
        g.setColor(Color.white);
        g.drawLine(192, 0, 192, RECORD_HEIGHT * 11);
        g.drawLine(384, 0, 384, RECORD_HEIGHT * 11);
    }

    private void renderRecordHeader(Graphics g, int tableX, int tableY) {
        g.drawRect(tableX, tableY, RECORD_WIDTH, RECORD_HEIGHT);
        g.drawLine(tableX + 192, 0, tableX + 192, RECORD_HEIGHT);
        g.drawLine(tableX + 384, 0, tableX + 384, RECORD_HEIGHT);

        g.drawString("Player", tableX, tableY);
        g.drawString("Score", tableX + 192, tableY);
        g.drawString("Date", tableX + 384, tableY);
    }

    private void renderRecord(Graphics g, int tableX, int tableY, HighScoreRecord record, int pos) {
        g.drawRect(tableX, tableY + (pos + 1) * RECORD_HEIGHT, RECORD_WIDTH, RECORD_HEIGHT);
        g.drawLine(tableX + 192,  tableY + (pos + 1) * RECORD_HEIGHT, tableX + 192, tableY + (pos + 1) * RECORD_HEIGHT + RECORD_HEIGHT);
        g.drawLine(tableX + 384, tableY + (pos + 1) * RECORD_HEIGHT, tableX + 384, tableY + (pos + 1) * RECORD_HEIGHT + RECORD_HEIGHT);

        if (record == null) {
            return;
        }

        g.drawString(record.getPlayerName(), tableX, tableY + (pos + 1) * RECORD_HEIGHT);
        g.drawString(record.getScore() + "", tableX + 192, tableY + (pos + 1) * RECORD_HEIGHT);
        g.drawString(record.getDate(), tableX + 384, tableY + (pos + 1) * RECORD_HEIGHT);
    }
}
