package com.coding.siteannonce.model;

import com.coding.siteannonce.dao.AnnonceDao;
import com.coding.siteannonce.dao.IAnnonceDao;

public class Launcher {

    private static final IAnnonceDao daoAcces = new AnnonceDao();

    public static void main(String[] args) {

        //daoAcces.getAllAnnonces().forEach(System.out::println);

        String param = "75";
        var hh = daoAcces.searchWithParam( param );
        System.out.println(hh);
    }


}
