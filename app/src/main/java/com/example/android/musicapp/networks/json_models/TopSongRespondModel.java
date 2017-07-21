package com.example.android.musicapp.networks.json_models;

/**
 * Created by valky on 7/21/2017.
 */

public class TopSongRespondModel {
    private TopSongFeed feed;

    public TopSongRespondModel(TopSongFeed feed) {
        this.feed = feed;
    }

    public TopSongFeed getFeed() {

        return feed;
    }

    public void setFeed(TopSongFeed feed) {
        this.feed = feed;
    }
}
