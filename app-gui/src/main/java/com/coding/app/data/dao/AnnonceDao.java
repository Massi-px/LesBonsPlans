package com.coding.app.data.dao;

import com.coding.app.data.connection.AppDataSource;
import com.coding.app.data.model.Annonce;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnnonceDao {

    private final AppDataSource dbSource;

    private static final String SQL_SELECT_BY_ID = """
            SELECT * FROM annonces WHERE id =?
            """;
    private static final String SQL_SELECT_ALL = """
            SELECT * FROM annonces
            """;
    private static final String SQL_INSERT = """
            INSERT INTO annonces (title, path, image, site, created_at) VALUES (?, ?, ?, ?, ?)
            """;
    private static final String SQL_UPDATE = """
            UPDATE annonces SET title = ?, path = ?, image = ?, site = ?, created_at = ? WHERE id = ?
            """;


    public AnnonceDao() {
        this.dbSource = new AppDataSource();
    }

    public void saveAnnonce() {
        try {
            Connection        connection = dbSource.getConnection();
            PreparedStatement statement  = connection.prepareStatement(SQL_INSERT);
            statement.setString(1, "title");
            statement.setString(2, "path");
            statement.setString(3, "image");
            statement.setString(4, "site");
            statement.setString(5, "created_at");
            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Une erreur s'est produite lors de la connexion à la base de données. " + e);
        }
    }

    public Annonce getAnnonceById(int id) {
        try {
            Connection        connection = dbSource.getConnection();
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

    public void updateAnnonce(Annonce annonce) {
        try {
            Connection        connection = dbSource.getConnection();
            PreparedStatement statement  = connection.prepareStatement(SQL_UPDATE);
            statement.setString(1, annonce.getTitle());
            statement.setString(2, annonce.getPath());
            statement.setString(3, annonce.getImage());
            statement.setString(4, annonce.getSite());
            statement.setTimestamp(5, annonce.getCreatedAt());
            statement.setInt(6, annonce.getId());
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
                resultSet.getString("path"),
                resultSet.getString("image"),
                resultSet.getString("site"),
                resultSet.getTimestamp("created_at")
        );
    }

}
