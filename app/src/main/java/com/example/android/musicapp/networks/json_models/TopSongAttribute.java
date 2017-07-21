package com.example.android.musicapp.networks.json_models;

/**
 * Created by valky on 7/21/2017.
 */

public class TopSongAttribute {
    private int height;

    public TopSongAttribute(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return height+"";
    }
}
