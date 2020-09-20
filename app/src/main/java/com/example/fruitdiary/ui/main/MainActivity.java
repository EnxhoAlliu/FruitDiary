package com.example.fruitdiary.ui.main;

import android.os.Bundle;

import com.example.fruitdiary.adapters.EntriesAdapter;
import com.example.fruitdiary.server.ServerSync;
import com.example.fruitdiary.R;
import com.example.fruitdiary.models.Entry;
import com.example.fruitdiary.presenters.FruitPresenter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


import java.util.List;

import retrofit2.Response;
import toothpick.Scope;
import toothpick.Toothpick;

public class MainActivity extends AppCompatActivity implements ServerSync {

    private FruitFragment fruitFragment;
    private EntriesFragment entriesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToothPick();
        setupSpinner();
        getFruitList();
    }

    private void initToothPick(){
        Scope appScope = Toothpick.openScope(this);
        Toothpick.inject(this, appScope);
    }

    private void setupSpinner(){
        Spinner spinner = (Spinner) findViewById(R.id.dynamic_spinner);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter
                .createFromResource(this, R.array.tab_array,
                        android.R.layout.simple_spinner_item);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                switch(position){
                    case 0:
                        openEntriesFragment();
                        break;
                    case 1:
                        openFruitsFragment();
                        break;
                    case 2:
                        openAboutFragment();
                        break;
                    default:
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
    }

    private void getFruitList(){
        new FruitPresenter(this);
    }

    private void openEntriesFragment(){
        removeFragment(fruitFragment);
        entriesFragment = new EntriesFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,entriesFragment).commitAllowingStateLoss();
    }


    private void openFruitsFragment(){
        removeFragment(entriesFragment);
        fruitFragment = new FruitFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,fruitFragment).commitAllowingStateLoss();
    }

    private void openAboutFragment(){

    }

    private void removeFragment(Fragment fragment){
        if(fragment == null){
            return;
        }
        getSupportFragmentManager().beginTransaction().remove(fragment).commitAllowingStateLoss();
    }

    @Override
    public void sync(Object object) {

    }

    @Override
    public void sync(Response response) {

    }

    @Override
    public void syncEntries(List<Entry> entries) {

    }
}