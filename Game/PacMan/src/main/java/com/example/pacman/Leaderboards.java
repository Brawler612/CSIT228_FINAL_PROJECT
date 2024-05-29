package com.example.pacman;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Leaderboards {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/oopdatabase";
    private static final String DB_USER = "your_username";
    private static final String DB_PASSWORD = "your_password";

    public Leaderboards() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void create(String name, int score) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            Create create = new Create();
            create.insertLeaderboard(connection, name, score);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Leaderboard> getTopScores(int limit) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            Read read = new Read();
            return read.getTopLeaderboards(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public void update(int id, String newName, int newScore) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            Update update = new Update();
            update.updateLeaderboard(connection, id, newName, newScore);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            Delete delete = new Delete();
            delete.deleteLeaderboard(connection, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}