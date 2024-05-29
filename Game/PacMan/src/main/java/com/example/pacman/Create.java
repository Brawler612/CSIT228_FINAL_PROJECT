package com.example.pacman;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Create {
    private static final String SQL_INSERT = "INSERT INTO leaderboard (name, score) VALUES (?, ?)";

    public void insertLeaderboard(Connection connection, String name, int score) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT)) {
            statement.setString(1, name);
            statement.setInt(2, score);
            statement.executeUpdate();
        }
    }
}