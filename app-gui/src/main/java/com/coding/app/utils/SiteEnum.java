package com.coding.app.utils;



public enum SiteEnum {

    LES_BONS_PLANS("Les Bons Plans", "http://localhost:8080/site_annonce_war/api/announcement?keyword="),
    LE_BON_COIN("Leboncoin", "https://www.leboncoin.fr/recherche?text=");


    private final String url;
    private final String name;


    SiteEnum(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }
}
