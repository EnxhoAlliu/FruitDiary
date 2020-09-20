package com.example.fruitdiary.server;

import com.example.fruitdiary.models.Entry;
import com.example.fruitdiary.models.Fruit;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface APIService {

    @GET("api/fruit")
    Call<List<Fruit>> getFruits();

    @GET("api/entries")
    Call<List<Entry>> getEntries();

    @DELETE("api/entries")
    void deleteAllEntries();

    @DELETE("api/entry/{entryId}")
    void deleteSpecificEntry(
            @Path("entryId") int entryID);
}