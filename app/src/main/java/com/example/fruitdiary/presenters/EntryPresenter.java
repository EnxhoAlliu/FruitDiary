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
    public static final int DELETE_ENTRY_REQ_CODE = 888;
    public static final int GET_ENTRIES_REQ_CODE = 111;
    public static final int DELETE_ALL_ENTRIES_REQ_CODE = 999;
    public static final int ADD_FRUIT_TO_ENTRY_RQ_CODE = 122;
    public static final int NEW_ENTRY_RQ_CODE = 133;
    private ServerSync serverSync;

    public EntryPresenter(ServerSync serverSync) {
        this.serverSync = serverSync;
    }

    public void getEntries() {
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

    public void deleteAllEntries() {
        RetrofitClient.getAPIService().deleteAllEntries().enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                    send(response.body(), DELETE_ALL_ENTRIES_REQ_CODE);
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });
    }

    public void deleteEntry(int entryID) {
        RetrofitClient.getAPIService().deleteSpecificEntry(entryID).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                send(response.body(), DELETE_ENTRY_REQ_CODE);
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });
    }

    public void addFruitToEntry(int entryID, int fruitID, int fruitAmount) {
        RetrofitClient.getAPIService().addFruitToEntry(entryID, fruitID, fruitAmount).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                send(response.body(), ADD_FRUIT_TO_ENTRY_RQ_CODE);
                Log.i(TAG, response.body().toString());
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });
    }

    public void addNewEntry(String date) {
        DateEntry dateEntry = new DateEntry(date);
        RetrofitClient.getAPIService().addNewEntry(dateEntry).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                send(response.body(), NEW_ENTRY_RQ_CODE);
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });
    }

    private void send(Object object, int requestCode) {
        if (object == null)
            serverSync.sync(null, 0);
        else
            serverSync.sync(object, requestCode);
    }

}
