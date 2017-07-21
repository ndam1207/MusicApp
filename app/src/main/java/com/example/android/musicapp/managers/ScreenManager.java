package com.example.android.musicapp.managers;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.android.musicapp.R;
import com.example.android.musicapp.databases.MusicTypeModel;

/**
 * Created by valky on 7/19/2017.
 */

public class ScreenManager {
    public static MusicTypeModel musicTypeClicked;
    public static void openFragment(FragmentManager fragmentManager, Fragment fragment, int layoutID, Fragment currentFragment, MusicTypeModel musicTypeModel){
        musicTypeClicked = musicTypeModel;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(layoutID, fragment);
        fragmentTransaction.commit();
    }
}
