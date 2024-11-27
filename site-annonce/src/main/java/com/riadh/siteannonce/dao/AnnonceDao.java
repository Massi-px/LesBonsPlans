package com.riadh.siteannonce.dao;

import com.riadh.siteannonce.connection.AppDataSource;
import com.riadh.siteannonce.model.Annonce;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnnonceDao implements IAnnonceDao {

    private final DataSource dataSource;

    private static final String SQL_SELECT_BY_ID = """
            SELECT * FROM annonces WHERE id =?
            """;

    private static final String SQL_SELECT_ALL = """
            SELECT * FROM annonces
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
    public Annonce getAnnonceById(int id) {

        try {
            Connection        connection = dataSource.getConnection();
            PreparedStatement statement  = connection.prepareStatement(SQL_SELECT_BY_ID);
            statement.setInt(1, id);
            ResultSet         resultSet  = statement.executeQuery();
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
        return annonce;
    }
}
