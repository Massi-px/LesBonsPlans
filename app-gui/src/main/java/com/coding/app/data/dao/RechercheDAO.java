package com.coding.app.data.dao;

import com.coding.app.data.connection.AppDataSource;
import com.coding.app.data.model.Recherche;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RechercheDAO {

    private final AppDataSource dbSource;

    private static final String SQL_SELECT_ALL = "SELECT * FROM recherches";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM recherches WHERE id = ?";
    private static final String SQL_INSERT = "INSERT INTO recherches (keywords, sites, frequency) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE recherches SET keywords =?, sites =?, frequency =? WHERE id =?";


    public RechercheDAO() {
        this.dbSource = new AppDataSource();
    }

    public List<Recherche> getAllRecherches() {
        List<Recherche> recherches = new ArrayList<>();
        try {
            Connection connection = dbSource.getConnection();
            PreparedStatement statement  = connection.prepareStatement(SQL_SELECT_ALL);
            ResultSet resultSet  = statement.executeQuery();

            while (resultSet.next()) {
                Recherche recherche = mapResultSetToRecherche(resultSet);
                recherches.add(recherche);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("An error occurred while executing the sql query. " + e);
        }
        return recherches;
    }

    public Recherche getRechercheById(int id) {
        Recherche recherche = null;
        try {
            Connection        connection = dbSource.getConnection();
            PreparedStatement statement  = connection.prepareStatement(SQL_SELECT_BY_ID);
            statement.setInt(1, id);
            ResultSet         resultSet  = statement.executeQuery();
            while (resultSet.next()) {
                recherche = mapResultSetToRecherche(resultSet);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("An error occurred while executing the sql query. " + e);
        }
        return recherche;
    }

    public void addRecherche(Recherche recherche) {
        try {
            Connection connection = dbSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT);
            statement.setString(1, recherche.getKeywords());
            statement.setString(2, recherche.getSites());
            statement.setInt(3, recherche.getFrequency());
            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.out.println("An error occurred while executing the sql query. " + e);
        }
    }

    public void updateRecherche(Recherche recherche) {
        try {
            Connection connection = dbSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE);
            statement.setString(1, recherche.getKeywords());
            statement.setString(2, recherche.getSites());
            statement.setInt(3, recherche.getFrequency());
            statement.setInt(4, recherche.getId());
            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.out.println("An error occurred while executing the sql query. " + e);
        }
    }


    private Recherche mapResultSetToRecherche(ResultSet resultSet) {
        Recherche recherche = new Recherche();
        try {
            recherche.setId(resultSet.getInt("id"));
            recherche.setKeywords(resultSet.getString("keywords"));
            recherche.setSites(resultSet.getString("sites"));
            recherche.setFrequency(resultSet.getInt("frequency"));
        } catch (SQLException e) {
            System.out.println("An error occurred while executing the sql query. " + e);
        }
        return recherche;
    }


}
