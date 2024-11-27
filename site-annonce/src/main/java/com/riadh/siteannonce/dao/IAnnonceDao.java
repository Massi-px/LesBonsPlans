package com.riadh.siteannonce.dao;

import com.riadh.siteannonce.model.Annonce;

import java.util.List;

public interface IAnnonceDao {

    List<Annonce> getAllAnnonces();
    Annonce getAnnonceById(int id);

}
