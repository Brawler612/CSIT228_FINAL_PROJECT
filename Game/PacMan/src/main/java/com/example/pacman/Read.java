package com.example.pacman;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Read {
    private static final String SQL_SELECT_ALL = "SELECT * FROM leaderboard ORDER BY score DESC LIMIT 10";

    public List<Leaderboard> getTopLeaderboards(Connection connection) throws SQLException {
        List<Leaderboard> leaderboards = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int score = resultSet.getInt("score");
                leaderboards.add(new Leaderboard(id, name, score));
            }
        }
        return leaderboards;
    }
}