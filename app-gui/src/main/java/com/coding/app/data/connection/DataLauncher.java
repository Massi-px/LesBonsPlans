package com.coding.app.data.connection;

import java.sql.SQLException;

public class DataLauncher {
    public static void main(String[] args) throws SQLException {
        AppDataSource appDataSource = new AppDataSource();

        appDataSource.getConnection();
    }
}
