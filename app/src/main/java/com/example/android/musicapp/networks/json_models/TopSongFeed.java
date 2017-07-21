package com.example.android.musicapp.networks.json_models;

import java.util.List;

/**
 * Created by valky on 7/21/2017.
 */

public class TopSongFeed {
    List<TopSongJSONModel> entry;

    public List<TopSongJSONModel> getEntry() {
        return entry;
    }

    public void setEntry(List<TopSongJSONModel> entry) {
        this.entry = entry;
    }

    public TopSongFeed(List<TopSongJSONModel> entry) {
        this.entry = entry;
    }
}
