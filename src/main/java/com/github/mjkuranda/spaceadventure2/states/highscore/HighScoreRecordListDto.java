package com.github.mjkuranda.spaceadventure2.states.highscore;

import java.io.Serializable;
import java.util.List;

public class HighScoreRecordListDto implements Serializable {

    private List<HighScoreRecord> records;

    public HighScoreRecordListDto(List<HighScoreRecord> records) {
        this.records = records;
    }

    public List<HighScoreRecord> getRecords() {
        return records;
    }
}
