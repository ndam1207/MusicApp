package com.example.android.musicapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android.musicapp.R;
import com.example.android.musicapp.adapters.MusicTypeAdapter;
import com.example.android.musicapp.databases.MusicTypeModel;
import com.example.android.musicapp.managers.ScreenManager;
import com.example.android.musicapp.networks.GetMusicTypes;
import com.example.android.musicapp.networks.RetrofitFactory;
import com.example.android.musicapp.networks.json_models.AllMusicTypesJSONModel;
import com.example.android.musicapp.networks.json_models.MusicTypeJSONModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MusicTypeFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.rv_music_types)
    RecyclerView rvMusicTypes;

    private List<MusicTypeModel> musicTypeModelList = new ArrayList<>();
    MusicTypeAdapter musicTypeAdapter;

    public MusicTypeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music_type, container, false);

        setupUI(view);
        loadData();

        return view;
    }

    private void loadData() {
        GetMusicTypes getMusicTypes = RetrofitFactory
                .getInstance().create(GetMusicTypes.class);

        getMusicTypes.getMusicTypes().enqueue(new Callback<AllMusicTypesJSONModel>() {
            @Override
            public void onResponse(Call<AllMusicTypesJSONModel> call, Response<AllMusicTypesJSONModel> response) {
                for (MusicTypeJSONModel musicTypeJSONMOdel:response.body().getSubgenres()){
                    MusicTypeModel musicTypeModel = new MusicTypeModel();
                    musicTypeModel.setId(musicTypeJSONMOdel.getId());
                    musicTypeModel.setKey(musicTypeJSONMOdel.getTranslation_key());
                    musicTypeModel.setImageID(getContext().getResources()
                            .getIdentifier("genre_x2_" + musicTypeJSONMOdel.getId(),
                                    "raw", getContext().getPackageName()));
                    musicTypeModelList.add(musicTypeModel);
                }

                musicTypeAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<AllMusicTypesJSONModel> call, Throwable t) {
                Toast.makeText(getContext(), "404: Not Found!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupUI(View view) {
        ButterKnife.bind(this, view);

        musicTypeAdapter = new MusicTypeAdapter(musicTypeModelList, getContext());
        rvMusicTypes.setAdapter(musicTypeAdapter);

        GridLayoutManager gridLayOutManager = new GridLayoutManager(getContext(), 2 , GridLayoutManager.VERTICAL, false);
        gridLayOutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position % 3 == 0 ? 2 : 1;
            }
        });

        rvMusicTypes.setLayoutManager(gridLayOutManager);
        musicTypeAdapter.setOnItemClick(this);
    }

    @Override
    public void onClick(View v) {
        MusicTypeModel musicTypeModel = (MusicTypeModel) v.getTag();
       // ScreenManager.openFragment(getActivity().getSupportFragmentManager(), R.id.layout_container, new TopSongFragment());
}
}
