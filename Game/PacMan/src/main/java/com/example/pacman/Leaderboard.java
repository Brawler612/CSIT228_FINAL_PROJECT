package com.example.pacman;

public class Leaderboard {
    private int id;
    private String name;
    private int score;
    private int level;

    public Leaderboard(int id, String name, int score, int level) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.level = level;
    }

    // Getters and setters

    @Override
    public String toString() {
        return String.format("%d. %s - %d - %d", id, name, score,level);
    }
}