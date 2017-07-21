package com.example.android.musicapp.networks.json_models;

/**
 * Created by valky on 7/21/2017.
 */

public class TopSongName {
    private String label;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public TopSongName(String label) {

        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
