package com.github.mjkuranda.spaceadventure2.states.highscore;

import java.io.Serializable;

public class HighScoreRecord implements Serializable {

    private String playerName;

    private int score;

    public HighScoreRecord(String playerName, int score) {
        this.playerName = playerName;
        this.score = score;
    }

    public String toString() {
        return "Player: " + playerName + ", gained: " + score;
    }
}
