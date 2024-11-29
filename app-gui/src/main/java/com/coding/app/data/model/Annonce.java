package com.coding.app.data.model;

import java.sql.Blob;
import java.sql.Timestamp;

public class Annonce {

    private int id;
    private String title;
    private String site;
    private String link;
    //private Blob image;
    private Timestamp createdAt;

    public Annonce() {
    }

    public Annonce(int id, String title, String site, String link, Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.site = site;
        this.link = link;
        this.createdAt = createdAt;
    }

    public Annonce(String title, String site, String link, Timestamp createdAt) {
        this.title = title;
        this.site = site;
        this.link = link;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Annonce{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", site='" + site + '\'' +
                ", link='" + link + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
