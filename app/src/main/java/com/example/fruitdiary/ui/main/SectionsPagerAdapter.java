package com.example.fruitdiary.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.fruitdiary.R;
import com.example.fruitdiary.ui.main.fragments.BlankFragment;
import com.example.fruitdiary.ui.main.fragments.EntriesFragment;
import com.example.fruitdiary.ui.main.fragments.FruitFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {

        switch(position){
            case 0:
                return EntriesFragment.newInstance();
            case 1:
                return FruitFragment.newInstance();
            case 2:
            default:
                return BlankFragment.newInstance();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getStringArray(R.array.tab_array)[position];
    }

    @Override
    public int getCount() {
        return mContext.getResources().getStringArray(R.array.tab_array).length;
    }
}