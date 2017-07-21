package com.example.android.musicapp.networks.json_models;

/**
 * Created by valky on 7/21/2017.
 */

public class TopSongArtist {
    private String label;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public TopSongArtist(String label) {

        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
