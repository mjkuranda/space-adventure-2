package com.github.mjkuranda.spaceadventure2;

/**
 * This class contains data about player
 * such as score points, shoot missiles, etc.
 */
public class PlayerData {

    private static PlayerData data;

    /** Player name */
    private String name;

    /** Player score points */
    private int score;

    /** Player distance */
    private float distance;

    /** Player data */
    private float vibration;

    private PlayerData() {
        //
    }

    /**
     * Returns player statistics
     * @return PlayerStatistics
     */
    public static PlayerData getInstance() {
        if (data == null) {
            data = new PlayerData();
        }

        return data;
    }

    /**
     * Clears the data
     */
    public void clear() {
        this.name = null;
        this.score = 0;
        this.distance = 0;
        this.vibration = 0;
    }

    /**
     * Resets data
     */
    public void reset() {
        this.score = 0;
        this.distance = 0;
        this.vibration = 0;
    }

    public void vibrate() {
        this.vibration += 15;
    }

    public void unvibrate() {
        this.vibration -= 0.25f;

        if (vibration < 0) {
            vibration = 0;
        }
    }

    public float getVibration() {
        return vibration;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public int getScore() {
        return score;
    }

    public void addDistance(float distance) {
        this.distance += distance;
    }

    public float getDistance() {
        return distance;
    }
}
