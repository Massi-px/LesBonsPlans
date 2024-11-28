package com.coding.app.utils;



public enum SiteEnum {

    LES_BONS_PLANS("Les Bon Plan", "http://localhost:8080/site_annonce_war/api/announcement?keyword="),
    LE_BON_COIN("Le bon coin", "https://www.leboncoin.fr/recherche?text=");


    private final String url;
    private final String site;


    SiteEnum(String site, String url) {
        this.site = site;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public String getSite() {
        return site;
    }
}
