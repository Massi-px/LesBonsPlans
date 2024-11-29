package com.coding.app.data.connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DataInitialiazer {

    private static final String CREATE_TABLE_ANNONCES = """
        CREATE TABLE IF NOT EXISTS annonces (
            id INT AUTO_INCREMENT PRIMARY KEY,
            title TEXT NOT NULL,
            path VARCHAR(2048) NOT NULL,
            image VARCHAR(2048),
            site TEXT NOT NULL,
            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        );
    """;

    private static final String CREATE_TABLE_RECHERCHES = """
        CREATE TABLE IF NOT EXISTS recherches (
            id INT AUTO_INCREMENT PRIMARY KEY,
            keywords TEXT NOT NULL,
            sites JSON,
            frequency INT NOT NULL
        );
    """;

    public static void initializeDatabase() {
        try (Connection connection = new AppDataSource().getConnection();
             Statement statement = connection.createStatement()) {

            statement.execute(CREATE_TABLE_ANNONCES);
            System.out.println("Table 'annonces' créée ou existe déjà.");

            statement.execute(CREATE_TABLE_RECHERCHES);
            System.out.println("Table 'recherches' créée ou existe déjà.");

        } catch (SQLException e) {
            System.out.println("Erreur lors de l'initialisation de la base de données : " + e.getMessage());
        }
    }
}
