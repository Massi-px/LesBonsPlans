package com.coding.app.data.connection;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

public class AppDataSource implements DataSource {

    private static final String DATABASE_PROPERTIES_FILE_PATH = "/config/db.properties";

    private static final String url = "jdbc:mariadb://localhost:3307/LesBonsPlansApp?serverTimezone=UTC&verifyServerCertificate=false&useSSL=true";
    private static final String username = "LBP";
    private static final String password = "root";
    private static final String driver = "org.mariadb.jdbc.Driver";

    static {
        try {
            Class.forName(driver);
            System.out.println("Connexion OK !");
        } catch (ClassNotFoundException e) {
            System.out.println("Le driver est introuvable: " + e);
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }
}