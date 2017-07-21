package com.example.android.musicapp.databases;

/**
 * Created by valky on 7/20/2017.
 */

public class TopSongModel {
    private String name;
    private String image;
    private String author;


    public TopSongModel() {
    }

    public TopSongModel(String name, String image, String author) {
        this.name = name;
        this.image = image;
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
