package com.example.android.musicapp.managers;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.android.musicapp.R;

/**
 * Created by valky on 7/19/2017.
 */

public class ScreenManager {
    public static void openFragment(FragmentManager fragmentManager, Fragment fragment, int layoutID){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.layout_container, fragment);
        fragmentTransaction.commit();
    }
}
