package com.example.fruitdiary.presenters;

import android.util.Log;

import com.example.fruitdiary.models.Fruit;
import com.example.fruitdiary.RetrofitClient;

import java.util.List;


import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FruitPresenter extends Presenter {
    private static final String TAG = "FRUIT_PRESENTER";

    public FruitPresenter(){
        RetrofitClient.getAPIService().getFruits().enqueue(new Callback<List<Fruit>>() {
            @Override
            public void onResponse(Call<List<Fruit>> call, Response<List<Fruit>> response) {
                Log.i(TAG, call.toString() + " ,,  " + response.toString() );
            }

            @Override
            public void onFailure(Call<List<Fruit>> call, Throwable t) {
                Log.i(TAG, call.toString() + " ,,  " + t.toString() );
            }
        });

    }

}
