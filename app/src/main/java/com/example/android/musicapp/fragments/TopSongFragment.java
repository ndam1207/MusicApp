package com.example.android.musicapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.musicapp.R;
import com.example.android.musicapp.adapters.TopSongAdapter;
import com.example.android.musicapp.databases.TopSongModel;
import com.example.android.musicapp.managers.ScreenManager;
import com.example.android.musicapp.networks.GetTopSongs;
import com.example.android.musicapp.networks.RetrofitFactory;
import com.example.android.musicapp.networks.json_models.TopSongImage;
import com.example.android.musicapp.networks.json_models.TopSongJSONModel;
import com.example.android.musicapp.networks.json_models.TopSongRespondModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by valky on 7/20/2017.
 */

public class TopSongFragment extends Fragment {
    @BindView(R.id.rv_top_songs)
    RecyclerView rvTopSongs;

    private List<TopSongModel> topSongModelList = new ArrayList<>();
    TopSongAdapter topSongAdapter;

    public TopSongFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top_song,container,false);

        setupUI(view);
        loadData();

        return view;
    }

    private void loadData() {
        final GetTopSongs getTopSongs = RetrofitFactory.getInstance().create(GetTopSongs.class);
        getTopSongs.getTopSongs(ScreenManager.musicTypeClicked.getId()).enqueue(new Callback<TopSongRespondModel>() {
            @Override
            public void onResponse(Call<TopSongRespondModel> call, Response<TopSongRespondModel> response) {
                List<TopSongJSONModel> entry = response.body().getFeed().getEntry();
                for (TopSongJSONModel topSongJSONModel : entry) {
                    TopSongModel topSongModel = new TopSongModel();
                    topSongModel.setName(topSongJSONModel.getSongName().getLabel());
                    topSongModel.setAuthor(topSongJSONModel.getSongArtist().getLabel());
                    List<TopSongImage> images = topSongJSONModel.getSongImage();
                    for (TopSongImage image : images) {
                        if (image.getAttributes().getHeight() == 170) {
                            topSongModel.setImage(image.getLabel());
                        }
                    }
                    topSongModelList.add(topSongModel);
                }
                topSongAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<TopSongRespondModel> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.toString());
                Toast.makeText(getContext(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupUI(View view) {
        ButterKnife.bind(this, view);

        topSongAdapter = new TopSongAdapter(topSongModelList, getContext());
        rvTopSongs.setAdapter(topSongAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        rvTopSongs.setLayoutManager(linearLayoutManager);


    }
}
