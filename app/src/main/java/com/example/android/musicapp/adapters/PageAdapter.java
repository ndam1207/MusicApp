package com.example.android.musicapp.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.android.musicapp.fragments.MusicTypeFragment;
import com.example.android.musicapp.fragments.DownloadFragment;
import com.example.android.musicapp.fragments.FavoriteFragment;

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
                fragment = new MusicTypeFragment();
                break;
            case 1:
                fragment = new FavoriteFragment();
                break;
            case 2:
                fragment = new DownloadFragment();
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
