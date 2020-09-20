package com.example.fruitdiary.presenters;

import android.util.Log;

import com.example.fruitdiary.server.ServerSync;
import com.example.fruitdiary.models.Fruit;
import com.example.fruitdiary.server.RetrofitClient;

import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FruitPresenter{

    private static final String TAG = "FRUIT_PRESENTER";
    public static List<Fruit> FRUIT_LIST;
    ServerSync serverSync;

    public FruitPresenter(final ServerSync serverSync){
       this.serverSync = serverSync;
    }

    public void getFruitList(){
        RetrofitClient.getAPIService().getFruits().enqueue(new Callback<List<Fruit>>() {
            @Override
            public void onResponse(Call<List<Fruit>> call, Response<List<Fruit>> response) {
                FRUIT_LIST = response.body();
                serverSync.sync(true);
            }

            @Override
            public void onFailure(Call<List<Fruit>> call, Throwable t) {
                Log.i(TAG, call.toString() + " ,,  " + t.toString() );
            }
        });
    }

}
