package com.example.fruitdiary;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.example.fruitdiary.presenters.EntryPresenter;
import com.example.fruitdiary.presenters.FruitPresenter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.fruitdiary.ui.main.SectionsPagerAdapter;

import java.security.Permission;

import toothpick.Scope;
import toothpick.Toothpick;

public class MainActivity extends AppCompatActivity {

    private String internetPermission = Manifest.permission.INTERNET;
    private int requestCode = 202;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        initToothPick();

    }

    private void initToothPick(){
        Scope appScope = Toothpick.openScope(this);
        Toothpick.inject(this, appScope);
    }


}