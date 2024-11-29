package com.coding.siteannonce.dao;

import com.coding.siteannonce.connection.AppDataSource;
import com.coding.siteannonce.model.Announcement;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AnnouncementDAO implements IAnnouncementDAO {

    private final DataSource db;

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
            title LIKE? OR description LIKE?
            """;

    public AnnouncementDAO() {
        this.db = new AppDataSource();
    }

    @Override
    public List<Announcement> getAll() {
        List<Announcement> accessList = new ArrayList<>();
        try {
            Connection        connection = db.getConnection();
            PreparedStatement statement  = connection.prepareStatement(SQL_SELECT_ALL);
            ResultSet         resultSet  = statement.executeQuery();

            while (resultSet.next()) {
                Announcement announcement = mapResultSetToAnnouncement( resultSet );
                accessList.add( announcement );
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("An error occurred while connecting to the database. " + e);
        }
        return accessList;
    }

    @Override
    public List<Announcement> searchWithParam(String param) {
        try {
            HashMap<Integer, Announcement> announcementMap   = new HashMap<>();
            Connection                     connection = db.getConnection();
            var                            paramSplit = param.split(" ");

            PreparedStatement statement = connection.prepareStatement(SQL_SEARCH_WITH_PARAM);
            for (String split : paramSplit) {
                statement.setString(1, "%" + split + "%");
                statement.setString(2, "%" + split + "%");
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Announcement announcement = mapResultSetToAnnouncement(resultSet);
                    announcementMap.put( announcement.getId(), announcement );
                }
            }
            connection.close();
            return announcementMap.values().stream().toList();
        } catch (SQLException e) {
            System.out.println("An error occurred while connecting to the database. " + e);
        }
        return List.of();
    }


    @Override
    public Announcement getById(int id) {

        try {
            Connection   connection = db.getConnection();
            PreparedStatement statement  = connection.prepareStatement(SQL_SELECT_BY_ID);
            statement.setInt(1, id);
            ResultSet    resultSet    = statement.executeQuery();
            Announcement announcement = null;
            while (resultSet.next()) {
                announcement = mapResultSetToAnnouncement( resultSet );
            }
            connection.close();
            return announcement;
        } catch (SQLException e) {
            System.out.println("An error occurred while connecting to the database. " + e);
        }
        return null;
    }

    private Announcement mapResultSetToAnnouncement(ResultSet resultSet) throws SQLException {
        Announcement announcement = new Announcement();
        announcement.setId(resultSet.getInt("id"));
        announcement.setTitle(resultSet.getString("title"));
        announcement.setDescription(resultSet.getString("description"));
        announcement.setPath( resultSet.getString("path"));
        announcement.setImage(resultSet.getString("image"));
        announcement.setLink("http://localhost:8080/site_annonce_war/detail?id=" + announcement.getId());
        System.out.println( announcement );
        return announcement;
    }
}
