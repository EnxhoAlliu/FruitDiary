package com.example.fruitdiary;

import com.example.fruitdiary.models.Entry;
import com.example.fruitdiary.models.Fruit;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;


public interface APIService {

    @GET("api/fruit")
    Call<List<Fruit>> getFruits();

    @GET("api/entries")
    Call<List<Entry>> getEntries();
}
