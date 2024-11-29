package com.coding.siteannonce.dao;

import com.coding.siteannonce.connection.AppDataSource;
import com.coding.siteannonce.model.Annonce;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AnnonceDao implements IAnnonceDao {

    private final DataSource dataSource;

    private static final String SQL_SELECT_BY_ID = """
            SELECT * FROM annonces WHERE id =?
            """;

    private static final String SQL_SELECT_ALL = """
            SELECT * FROM annonces
            """;

    private static final String SQL_SEARCH_WITH_PARAM = """
            SELECT *
            FROM annonces
            WHERE
            title LIKE?
            OR description LIKE?
            """;

    public AnnonceDao() {
        this.dataSource = new AppDataSource();
    }

    @Override
    public List<Annonce> getAllAnnonces() {
        List<Annonce> accessList = new ArrayList<>();
        try {
            Connection        connection = dataSource.getConnection();
            PreparedStatement statement  = connection.prepareStatement(SQL_SELECT_ALL);
            ResultSet         resultSet  = statement.executeQuery();

            while (resultSet.next()) {
                Annonce annonce = mapResultSetToAnnonces( resultSet );
                accessList.add( annonce );
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Une erreur s'est produite lors de la connexion à la base de données. " + e);
        }
        return accessList;
    }

    @Override
    public List<Annonce> searchWithParam(String param) {
        try {
            HashMap<Integer, Annonce> annonces = new HashMap<>();
            Connection connection = dataSource.getConnection();
            var paramSplit = param.split(" ");

            PreparedStatement statement = connection.prepareStatement(SQL_SEARCH_WITH_PARAM);
            for (String split : paramSplit) {
                statement.setString(1, "%" + split + "%");
                statement.setString(2, "%" + split + "%");
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Annonce annonce = mapResultSetToAnnonces(resultSet);
                    annonces.put(annonce.getId(), annonce);
                }
            }
            connection.close();
            return annonces.values().stream().toList();
        } catch (SQLException e) {
            System.out.println("Une erreur s'est produite lors de la connexion à la base de données. " + e);
        }
        return List.of();
    }


    @Override
    public Annonce getAnnonceById(int id) {

        try {
            Connection   connection = dataSource.getConnection();
            PreparedStatement statement  = connection.prepareStatement(SQL_SELECT_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet  = statement.executeQuery();
            Annonce annonce = null;
            while (resultSet.next()) {
                annonce = mapResultSetToAnnonces( resultSet );
            }
            connection.close();
            return annonce;
        } catch (SQLException e) {
            System.out.println("Une erreur s'est produite lors de la connexion à la base de données. " + e);
        }
        return null;
    }

    private Annonce mapResultSetToAnnonces(ResultSet resultSet) throws SQLException {
        Annonce annonce = new Annonce();
        annonce.setId(resultSet.getInt("id"));
        annonce.setTitle(resultSet.getString("title"));
        annonce.setDescription(resultSet.getString("description"));
        annonce.setPath( resultSet.getString("path"));
        annonce.setImage(resultSet.getString("image"));
        annonce.setLink("http://localhost:8080/site_annonce_war/detail?id=" + annonce.getId());
        System.out.println(annonce);
        return annonce;
    }
}
