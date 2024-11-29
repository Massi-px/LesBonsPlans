package com.coding.app.data.connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DataInitializer {

    private static final String CREATE_TABLE_ANNONCES = """
        CREATE TABLE IF NOT EXISTS annonces (
            id INT AUTO_INCREMENT PRIMARY KEY,
            title TEXT,
            path VARCHAR(2048),
            image BLOB,
            site TEXT,
            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        );
    """;

    private static final String CREATE_TABLE_RECHERCHES = """
        CREATE TABLE IF NOT EXISTS recherches (
            id INT AUTO_INCREMENT PRIMARY KEY,
            keywords TEXT,
            sites TEXT,
            frequency INT
        );
    """;

    public static void initializeDatabase(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(CREATE_TABLE_ANNONCES);
            statement.execute(CREATE_TABLE_RECHERCHES);
        } catch (SQLException e) {
            System.out.println("Error initializing database : " + e.getMessage());
        }
    }
}
