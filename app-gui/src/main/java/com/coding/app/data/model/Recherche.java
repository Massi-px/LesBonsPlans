package com.coding.app.data.model;

public class Recherche {

    private int id;
    private String keywords;
    private String sites;
    private int frequency;

    public Recherche() {
    }

    public Recherche(int id, String keywords, String sites, int frequency) {
        this.id = id;
        this.keywords = keywords;
        this.sites = sites;
        this.frequency = frequency;
    }

    public Recherche(String keywords, String sites, int frequency) {
        this.keywords = keywords;
        this.sites = sites;
        this.frequency = frequency;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getSites() {
        return sites;
    }

    public void setSites(String sites) {
        this.sites = sites;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return "Recherche{" +
                "id=" + id +
                ", keywords='" + keywords + '\'' +
                ", sites='" + sites + '\'' +
                ", frequence=" + frequency +
                '}';
    }
}
