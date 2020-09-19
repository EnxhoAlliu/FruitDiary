package com.example.fruitdiary.presenters;

import android.util.Log;

import com.example.fruitdiary.models.Entry;
import com.example.fruitdiary.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EntryPresenter extends Presenter {
    private static final String TAG = "ENTRY_PRESENTER";

    public EntryPresenter() {
        RetrofitClient.getInstance().getEntries().enqueue(new Callback<List<Entry>>() {
            @Override
            public void onResponse(Call<List<Entry>> call, Response<List<Entry>> response) {
                Log.i(TAG, call.toString() + " ,,  " + response.toString());
            }

            @Override
            public void onFailure(Call<List<Entry>> call, Throwable t) {

            }
        });
    }

}
