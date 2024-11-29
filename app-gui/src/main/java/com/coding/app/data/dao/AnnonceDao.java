package com.coding.app.data.dao;

import com.coding.app.data.connection.AppDataSource;
import com.coding.app.data.model.Annonce;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnnonceDao {

    private final DataSource dbSource;

    private static final String SQL_SELECT_BY_ID = """
            SELECT * FROM annonces WHERE id =?
            """;
    private static final String SQL_SELECT_BY_TITLE_AND_LINK = """
            SELECT * FROM annonces WHERE title = ? AND path = ?
            """;
    private static final String SQL_SELECT_ALL = """
            SELECT * FROM annonces
            """;
    private static final String SQL_INSERT = """
            INSERT INTO annonces (title, site, path,image, created_at) VALUES (?, ?, ?, ?, ?)
            """;
    private static final String SQL_UPDATE = """
            UPDATE annonces SET title = ?, site = ? , path = ?, created_at = ?, image=? WHERE id = ?
            """;
    private static final String SQL_DELETE = "DELETE FROM annonces WHERE id = ?";


    public AnnonceDao() {
        this.dbSource = new AppDataSource();
    }

    public void saveAnnonce(Annonce annonce) {
        if (!isAnnonceExists(annonce)) {
            try {
                Connection connection = dbSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_INSERT);
                statement.setString(1, annonce.getTitle());
                statement.setString(2, annonce.getSite() );
                statement.setString(3, annonce.getPath());
                statement.setBlob(4, annonce.getImage());
                statement.setTimestamp(5, annonce.getCreatedAt());
                statement.executeUpdate();
                connection.close();
            } catch (SQLException e) {
                System.out.println("Une erreur s'est produite lors de la connexion à la base de données. " + e);
            }
        }
    }

    private boolean isAnnonceExists(Annonce annonce) {
        try {
            Connection connection = dbSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_TITLE_AND_LINK);
            statement.setString(1, annonce.getTitle());
            statement.setString(2, annonce.getPath());
            ResultSet rs = statement.executeQuery();
            boolean exists = rs.next();
            connection.close();
            return exists;
        } catch (SQLException e) {
            System.out.println("Une erreur s'est produite lors de la connexion à la base de données. " + e);
        }
        return false;
    }

    public Annonce getAnnonceById(int id) {
        try {
            Connection connection = dbSource.getConnection();
            PreparedStatement statement  = connection.prepareStatement(SQL_SELECT_BY_ID);
            statement.setInt(1, id);
            ResultSet rs      = statement.executeQuery();
            Annonce   annonce = null;
            while (rs.next()) {
                annonce = mapResultSetToAnnonces( rs );
            }
            connection.close();
            return annonce;
        } catch (SQLException e) {
            System.out.println("Une erreur s'est produite lors de la connexion à la base de données. " + e);
        }
        return null;
    }

    public List<Annonce> getAllAnnonces() {
        try {
            Connection        connection = dbSource.getConnection();
            PreparedStatement statement  = connection.prepareStatement(SQL_SELECT_ALL);
            ResultSet rs = statement.executeQuery();
            List<Annonce> annonces = new ArrayList<>();
            while (rs.next()) {
                Annonce annonce = mapResultSetToAnnonces( rs );
                annonces.add(annonce);
            }
            connection.close();
            return annonces;
        } catch (SQLException e) {
            System.out.println("Une erreur s'est produite lors de la connexion à la base de données. " + e);
        }
        return List.of();
    }



    public void deleteAnnonce(int id) {
        try {
            Connection connection = dbSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE);
            statement.setInt(1, id);
            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Une erreur s'est produite lors de la connexion à la base de données. " + e);
        }
    }


    private Annonce mapResultSetToAnnonces(ResultSet resultSet) throws SQLException {
        return new Annonce(
                resultSet.getInt("id"),
                resultSet.getString("title"),
                resultSet.getString("site"),
                resultSet.getString("path"),
                resultSet.getBlob("image"),
                resultSet.getTimestamp("created_at")
        );
    }
}
