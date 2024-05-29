package com.example.pacman;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Create {
    private static final String SQL_INSERT = "INSERT INTO leaderboard (playerName, score, level) VALUES (?, ?, ?)";

    public void insertLeaderboard(Connection connection, String playerName, int score, int level) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT)) {
            statement.setString(1, playerName);
            statement.setInt(2, score);
            statement.setInt(3, level);
            statement.executeUpdate();
        }
    }
}