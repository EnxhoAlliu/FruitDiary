package com.example.fruitdiary.server;

import com.example.fruitdiary.models.DateEntry;
import com.example.fruitdiary.models.Entry;
import com.example.fruitdiary.models.Fruit;

import java.util.Date;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface APIService {

    @GET("api/fruit")
    Call<List<Fruit>> getFruits();

    @GET("api/entries")
    Call<List<Entry>> getEntries();

    @DELETE("api/entries")
    void deleteAllEntries();

    @DELETE("api/entry/{entryId}")
    Call<Object> deleteSpecificEntry(
            @Path("entryId") int entryID);

    @POST("api/entry/{entryId}/fruit/{fruitId}")
    Call<Object> addFruitToEntry(
            @Path("entryId") int entryID,
            @Path("fruitId") int fruitID,
            @Query("amount") int amount
    );

    @Headers({"Content-Type: application/json"})
    @POST("api/entries")
    Call<Object> addNewEntry(
            @Body DateEntry dateEntry
    );
}
