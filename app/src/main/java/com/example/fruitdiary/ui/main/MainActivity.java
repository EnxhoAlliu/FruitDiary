package com.example.fruitdiary.ui.main;

import android.os.Bundle;

import com.example.fruitdiary.server.ServerSync;
import com.example.fruitdiary.R;
import com.example.fruitdiary.models.Entry;
import com.example.fruitdiary.presenters.FruitPresenter;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.List;

import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements ServerSync {

    private FruitFragment fruitFragment;
    private EntriesFragment entriesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getFruitList();
    }

    private void getFruitList() {
        new FruitPresenter(this).getFruitList();
    }

    private void removeFragment(Fragment fragment) {
        if (fragment == null) {
            return;
        }
        getSupportFragmentManager().beginTransaction().remove(fragment).commitAllowingStateLoss();
    }

    private void displayTabs() {
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }

    @Override
    public void sync(Object object) {
        if (object.equals(true)) {
            displayTabs();
        }
    }

    @Override
    public void sync(Response response) {

    }

    @Override
    public void syncEntries(List<Entry> entries) {

    }
}