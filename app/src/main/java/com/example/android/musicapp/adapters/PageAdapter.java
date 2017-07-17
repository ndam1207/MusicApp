package com.example.android.musicapp.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.android.musicapp.fragments.AllMusicFragment;
import com.example.android.musicapp.fragments.DownloadMusicFragment;
import com.example.android.musicapp.fragments.FavoriteMusicFragment;

/**
 * Created by valky on 7/17/2017.
 */

public class PageAdapter extends FragmentStatePagerAdapter {
    public PageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new AllMusicFragment();
                break;
            case 1:
                fragment = new FavoriteMusicFragment();
                break;
            case 2:
                fragment = new DownloadMusicFragment();
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
