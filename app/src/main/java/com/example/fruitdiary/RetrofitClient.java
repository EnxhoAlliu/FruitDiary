package com.example.fruitdiary;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String baseUrl = "https://fruitdiary.test.themobilelife.com/";
    private static final int timeOut = 2;
    private static Retrofit retrofitClient;
    private Gson gson = new GsonBuilder().setLenient().create();

    public static APIService getInstance(){
        if(retrofitClient == null){
            retrofitClient = new RetrofitClient().createRetrofitClient();
        }
        return retrofitClient.create(APIService.class);
    }


    private Retrofit createRetrofitClient(){
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(createHttpClient())
                .build();
    }

    private OkHttpClient createHttpClient(){
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.readTimeout(timeOut, TimeUnit.MINUTES)
                .connectTimeout(timeOut, TimeUnit.MINUTES)
                .addInterceptor(new HttpLoggingInterceptor());
        return httpClient.build();
    }

}
