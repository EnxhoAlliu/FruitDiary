package com.example.fruitdiary.ui.main;

import android.os.Bundle;
import android.os.Handler;

import com.example.fruitdiary.server.ServerSync;
import com.example.fruitdiary.R;
import com.example.fruitdiary.models.Entry;
import com.example.fruitdiary.presenters.FruitPresenter;
import com.example.fruitdiary.ui.main.fragments.EntriesFragment;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.List;


import static com.example.fruitdiary.presenters.FruitPresenter.GET_FRUIT_LIST_RQ_CODE;


public class MainActivity extends AppCompatActivity implements ServerSync, FragmentWatcher {

    private boolean backPressed;
    private Fragment entriesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getFruitList();
    }

    private void getFruitList() {
        new FruitPresenter(this).getFruitList();
    }

    @Override
    public void sync(Object object, int requestCode) {
        if (object.equals(true) && requestCode == GET_FRUIT_LIST_RQ_CODE) {
            displayTabs();
        }
    }


    private void displayTabs() {
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(), this);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }


    @Override
    public void syncEntries(List<Entry> entries) {

    }

    @Override
    public void watchFragment(Fragment fragment) {
        entriesFragment = fragment;
    }

    @Override
    public void onBackPressed() {
        Handler smallHandler = new Handler();
        if (!backPressed) {
            ((EntriesFragment) entriesFragment).requestEntriesList();
            ((EntriesFragment) entriesFragment).showEntriesList();
            backPressed = true;
            smallHandler.postDelayed(() -> backPressed = false, 700);
        } else {
            smallHandler.removeCallbacks(() -> backPressed = false);
            super.onBackPressed();
        }

    }
}