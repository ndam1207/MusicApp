package com.example.android.musicapp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.example.android.musicapp.adapters.PageAdapter;
import com.example.android.musicapp.networks.GetMusicTypes;
import com.example.android.musicapp.networks.RetrofitFactory;
import com.example.android.musicapp.networks.json_models.AllMusicTypesJSONModel;
import com.example.android.musicapp.networks.json_models.MusicTypeJSONModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TabLayout tlChoices;
    ViewPager vpContent;
    PageAdapter pageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        GetMusicTypes getMusicTypes = RetrofitFactory
//                .getInstance().create(GetMusicTypes.class);
//
//        getMusicTypes.getMusicTypes().enqueue(new Callback<AllMusicTypesJSONModel>() {
//            @Override
//            public void onResponse(Call<AllMusicTypesJSONModel> call, Response<AllMusicTypesJSONModel> response) {
//                for (MusicTypeJSONModel musicTyoeJSONMOdel:response.body().getSubgenres()){
//                    Log.d("KIEMTRA",musicTyoeJSONMOdel.getId());
//                    Log.d("KIEMTRA",musicTyoeJSONMOdel.getTranslation_key());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<AllMusicTypesJSONModel> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "404: Not Found!", Toast.LENGTH_SHORT).show();
//            }
//        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pageAdapter = new PageAdapter (getSupportFragmentManager());

        vpContent = (ViewPager) findViewById(R.id.vp_content);
        vpContent.setAdapter(pageAdapter);

        tlChoices = (TabLayout) findViewById(R.id.tl_choices);
        tlChoices.setupWithViewPager(vpContent);

        vpContent.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tlChoices));

        this.setTabLayout(tlChoices);

        tlChoices.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vpContent.setCurrentItem(tab.getPosition());
                tab.getIcon().setAlpha(255);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setAlpha(100);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                tab.getIcon().setAlpha(100);
            }
        });

    }

    private void setTabLayout(TabLayout tab){
        tab.getTabAt(0).setIcon(R.drawable.ic_dashboard_black_24dp);
        tab.getTabAt(1).setIcon(R.drawable.ic_favorite_black_24dp);
        tab.getTabAt(2).setIcon(R.drawable.ic_file_download_black_24dp);
        tab.getTabAt(0).getIcon().setAlpha(100);
        tab.getTabAt(1).getIcon().setAlpha(100);
        tab.getTabAt(2).getIcon().setAlpha(100);
    }
}
