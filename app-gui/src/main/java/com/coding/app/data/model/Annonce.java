package com.coding.app.data.model;

import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Objects;

public class Annonce {

    private int id;
    private String title;
    private String site;
    private String path;
    private Blob image;
    private Timestamp createdAt;

    public Annonce() {
    }

    public Annonce(int id, String title, String site, String path, Blob image, Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.site = site;
        this.path = path;
        this.image = image;
        this.createdAt = createdAt;
    }

    public Annonce(String title, String site, String path, Blob image, Timestamp createdAt) {
        this.title = title;
        this.site = site;
        this.path = path;
        this.image = image;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Title: " + title + "\nSite: " + site + "\nLink: " + path + "\nDate: " + createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Annonce annonce = (Annonce) o;
        return Objects.equals( title, annonce.title ) && Objects.equals( site,
                annonce.site ) && Objects.equals( path, annonce.path ) && Objects.equals( image,
                annonce.image );
    }

    @Override
    public int hashCode() {
        return Objects.hash( title, site, path, image );
    }
}
