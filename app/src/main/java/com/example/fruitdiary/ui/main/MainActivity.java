package com.example.fruitdiary.ui.main;

import android.os.Bundle;

import com.example.fruitdiary.APISync;
import com.example.fruitdiary.R;
import com.example.fruitdiary.Utils;
import com.example.fruitdiary.models.Entry;
import com.example.fruitdiary.models.Fruit;
import com.example.fruitdiary.presenters.FruitPresenter;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


import java.util.List;

import retrofit2.Response;
import toothpick.Scope;
import toothpick.Toothpick;

public class MainActivity extends AppCompatActivity implements APISync {


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

    }

    private void openFruitsFragment(){
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new FruitFragment()).commitAllowingStateLoss();
    }

    private void openAboutFragment(){

    }

    @Override
    public void sync(Response response) {

    }

    @Override
    public void syncFruits(List<Fruit> fruits) {
        Utils.FRUIT_LIST = fruits;
    }

    @Override
    public void syncEntries(List<Entry> entries) {

    }
}