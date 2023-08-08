package com.github.mjkuranda.spaceadventure2.states.highscore;

import java.io.Serializable;

public class HighScoreRecord implements Serializable, Comparable<HighScoreRecord> {

    private String playerName;

    private int score;

    private String date;

    public HighScoreRecord(String playerName, int score, String date) {
        this.playerName = playerName;
        this.score = score;
        this.date = date;
    }

    @Override
    public int compareTo(HighScoreRecord record) {
        return record.score - score;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getScore() {
        return score;
    }

    public String getDate() {
        return date;
    }

    public String toString() {
        return playerName + ":" + score + ":" + date;
    }
}
