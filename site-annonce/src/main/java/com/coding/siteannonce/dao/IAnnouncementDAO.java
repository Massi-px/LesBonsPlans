package com.coding.siteannonce.dao;

import com.coding.siteannonce.model.Announcement;

import java.util.List;

public interface IAnnouncementDAO {

    List<Announcement> getAll();
    List<Announcement> searchWithParam(String param);
    Announcement getById(int id);

}
