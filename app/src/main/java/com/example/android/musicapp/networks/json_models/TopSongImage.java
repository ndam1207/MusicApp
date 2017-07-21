package com.example.android.musicapp.networks.json_models;

/**
 * Created by valky on 7/21/2017.
 */

public class TopSongImage {
    private String label;
    private TopSongAttribute attributes;

    public TopSongImage(String label, TopSongAttribute attributes) {
        this.label = label;
        this.attributes = attributes;
    }

    public String getLabel() {

        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public TopSongAttribute getAttributes() {
        return attributes;
    }

    public void setAttributes(TopSongAttribute attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "TopSongImage{" +
                "label='" + label + '\'' +
                ", attributes=" + attributes +
                '}';
    }
}
