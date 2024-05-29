package com.example.pacman.LeaderBoard;

public class Leaderboard {
    private int id;
    public String playerName;
    public int score;
    public int level;

    public Leaderboard(int id, String playerName, int score, int level) {
        this.id = id;
        this.playerName = playerName;
        this.score = score;
        this.level = level;
    }

    // Getters and setters

    @Override
    public String toString() {
        return String.format("%d. %s - %d - %d", id, playerName, score,level);
    }
}