package com.example.fruitdiary.presenters;

import android.util.Log;

import com.example.fruitdiary.models.Entry;
import com.example.fruitdiary.server.RetrofitClient;
import com.example.fruitdiary.server.ServerSync;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EntryPresenter extends Presenter {
    private static final String TAG = "ENTRY_PRESENTER";
    private ServerSync serverSync;

    @Inject
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
        RetrofitClient.getAPIService().deleteSpecificEntry(entryID);
    }

    public void addFruitToEntry(int entryID, int fruitID, int fruitAmount){

    }

}
