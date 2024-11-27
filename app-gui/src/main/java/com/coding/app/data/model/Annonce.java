package com.coding.app.data.model;

import java.sql.Timestamp;

public class Annonce {

    private int id;
    private String title;
    private String path;
    private String image;
    private String site;
    private Timestamp createdAt;

    public Annonce() {
    }

    public Annonce(int id, String title, String path, String image, String site, Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.path = path;
        this.image = image;
        this.site = site;
        this.createdAt = createdAt;
    }

    public Annonce(String title, String path, String image, String site, Timestamp createdAt) {
        this.title = title;
        this.path = path;
        this.image = image;
        this.site = site;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
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
                ", path='" + path + '\'' +
                ", image='" + image + '\'' +
                ", site='" + site + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
