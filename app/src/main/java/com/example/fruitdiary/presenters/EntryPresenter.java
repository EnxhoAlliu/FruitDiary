package com.example.fruitdiary.presenters;

import android.util.Log;

import com.example.fruitdiary.models.DateEntry;
import com.example.fruitdiary.models.Entry;
import com.example.fruitdiary.server.RetrofitClient;
import com.example.fruitdiary.server.ServerSync;

import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EntryPresenter {
    private static final String TAG = "ENTRY_PRESENTER";
    private ServerSync serverSync;

    public EntryPresenter(ServerSync serverSync) {
        this.serverSync = serverSync;
    }

    public void getEntries(){
        RetrofitClient.getAPIService().getEntries().enqueue(new Callback<List<Entry>>() {
            @Override
            public void onResponse(Call<List<Entry>> call, Response<List<Entry>> response) {
                serverSync.syncEntries(response.body());
            }

            @Override
            public void onFailure(Call<List<Entry>> call, Throwable t) {

            }
        });
    }

    public void deleteAllEntries(){
        RetrofitClient.getAPIService().deleteAllEntries();
    }

    public void deleteEntry(int entryID){
        RetrofitClient.getAPIService().deleteSpecificEntry(entryID).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                serverSync.sync(response.body());
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });
    }

    public void addFruitToEntry(int entryID, int fruitID, int fruitAmount){
        RetrofitClient.getAPIService().addFruitToEntry(entryID, fruitID, fruitAmount).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                    serverSync.sync(response.body());
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });
    }

    public void addNewEntry(String date){
        DateEntry dateEntry = new DateEntry(date);
        RetrofitClient.getAPIService().addNewEntry(dateEntry).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                serverSync.sync(response.body());
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });
    }

}
