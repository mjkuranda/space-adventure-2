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

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < records.size(); i++) {
            var record = records.get(i);

            str.append(record.toString());

            if (i < records.size() - 1) {
                str.append(",");
            }
        }

        return str.toString();
    }
}
