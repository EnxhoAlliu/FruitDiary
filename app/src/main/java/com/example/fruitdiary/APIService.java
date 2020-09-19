package com.example.fruitdiary;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;


public interface APIService {

    @GET("api/fruit")
    Call<List<Fruit>> getFruits();
}
