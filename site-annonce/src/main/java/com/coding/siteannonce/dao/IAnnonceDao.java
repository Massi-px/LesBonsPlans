package com.coding.siteannonce.dao;

import com.coding.siteannonce.model.Annonce;

import java.util.List;

public interface IAnnonceDao {

    List<Annonce> getAllAnnonces();
    List<Annonce> searchWithParam(String param);
    Annonce getAnnonceById(int id);

}
