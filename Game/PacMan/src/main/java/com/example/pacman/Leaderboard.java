package com.example.pacman;

public class Leaderboard {
    private int id;
    private String name;
    private int score;

    public Leaderboard(int id, String name, int score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    // Getters and setters

    @Override
    public String toString() {
        return String.format("%d. %s - %d", id, name, score);
    }
}