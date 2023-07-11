package com.github.mjkuranda.spaceadventure2;

/**
 * This class contains data about player
 * such as score points, shoot missiles, etc.
 */
public class PlayerStatistics {

    private static PlayerStatistics stats;

    /** Player score points */
    private int score;

    /** Player distance */
    private float distance;

    private PlayerStatistics() {
        //
    }

    /**
     * Returns player statistics
     * @return PlayerStatistics
     */
    public static PlayerStatistics getInstance() {
        if (stats == null) {
            stats = new PlayerStatistics();
        }

        return stats;
    }

    /**
     * Clears the data
     */
    public void clear() {
        this.score = 0;
        this.distance = 0;
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
