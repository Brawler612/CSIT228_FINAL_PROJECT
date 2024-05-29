package com.example.pacman.Account;

import com.example.pacman.Config;

public class Account {
    private static String DB_URL;
    public static String USERNAME;
    public static String PASSWORD;

    public Account() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            DB_URL = Config.getInstance().DB_URL;
            USERNAME = Config.getInstance().DB_USER;
            PASSWORD = Config.getInstance().DB_PASSWORD;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void login(String username) {
        //final String SQL_INSERT = "INSERT INTO leaderboard (playerName, score, rank) VALUES (?, ?, ?)";
    }
}
