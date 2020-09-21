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
    private FragmentWatcher fragmentWatcher;

    public SectionsPagerAdapter(Context context, FragmentManager fm, FragmentWatcher fragmentWatcher) {
        super(fm);
        mContext = context;
        this.fragmentWatcher = fragmentWatcher;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch(position){
            case 0:
                fragment =  EntriesFragment.newInstance();
                fragmentWatcher.watchFragment(fragment);
                break;
            case 1:
                fragment =  FruitFragment.newInstance();
                break;
            case 2:
            default:
                fragment =  BlankFragment.newInstance();
        }

        return fragment;
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