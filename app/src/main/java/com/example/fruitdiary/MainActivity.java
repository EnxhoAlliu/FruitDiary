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
        checkPermissions();


    }


    public void checkPermissions() {

        // Checking if permission is not granted
        if (checkSelfPermission(internetPermission) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{internetPermission},
                            requestCode);
        }else{
            new FruitPresenter();
            new EntryPresenter();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == this.requestCode){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                new FruitPresenter();
                new EntryPresenter();
            }
        }
    }
}