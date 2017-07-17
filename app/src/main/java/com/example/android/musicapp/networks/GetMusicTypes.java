package com.example.android.musicapp.networks;

import com.example.android.musicapp.networks.json_models.AllMusicTypesJSONModel;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by valky on 7/15/2017.
 */

public interface GetMusicTypes {
    @GET("api")
    Call<AllMusicTypesJSONModel> getMusicTypes();
}
