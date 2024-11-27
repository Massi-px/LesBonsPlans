package com.riadh.siteannonce.model;

import com.riadh.siteannonce.dao.AnnonceDao;
import com.riadh.siteannonce.dao.IAnnonceDao;

public class Launcher {

    private static final IAnnonceDao daoAcces = new AnnonceDao();

    public static void main(String[] args) {

        //daoAcces.getAllAnnonces().forEach(System.out::println);

        var hh = daoAcces.getAnnonceById(1);
        System.out.println(hh);
    }


}
