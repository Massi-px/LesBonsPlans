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

    private static final String DATABASE_PROPERTIES_FILE_PATH = "db.properties";
    private static String url;
    private static String username;
    private static String password;
    private static String driver;

    static {
        Properties props = new Properties();
        InputStream inputStream = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream(DATABASE_PROPERTIES_FILE_PATH);

        try {
            props.load(inputStream);
            url = props.getProperty("url");
            username = props.getProperty("username");
            password = props.getProperty("password");
            driver = props.getProperty("driver");

            try {
                Class.forName(driver);
                System.out.println("Connexion OK !");
            } catch (ClassNotFoundException e) {
                System.out.println("le drive est introuvable " + e);
            }

        } catch (IOException e) {
            System.out.println("Une erreur s'est produite lors de la lecture" +
                    " du fichier propertie de la base de données, e");
        }
    }




    @Override
    public Connection getConnection() throws SQLException {
        var c = DriverManager.getConnection(url, username, password);
        DataInitializer.initializeDatabase(c);
        return c;
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