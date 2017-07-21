package com.example.android.musicapp.networks;

import com.example.android.musicapp.networks.json_models.TopSongRespondModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by valky on 7/21/2017.
 */

public interface GetTopSongs {
    @GET("https://itunes.apple.com/us/rss/topsongs/limit=50/genre={user}/explicit=true/json")
    Call<TopSongRespondModel> getTopSongs(@Path("user") String user);
}
