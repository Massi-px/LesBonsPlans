package com.coding.siteannonce.model;

public class Annonce {

    private int id;
    private String title;
    private String path;
    private String image;
    private String description;
    private String link;

    public Annonce() {
    }

    public Annonce(int id, String title, String path, String image, String description, String link) {
        this.id = id;
        this.title = title;
        this.path = path;
        this.image = image;
        this.description = description;
        this.link = link;
    }

    public Annonce(String title, String path, String image, String description) {
        this.title = title;
        this.path = path;
        this.image = image;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }


    @Override
    public String toString() {
        return "Annoces{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", path='" + path + '\'' +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
