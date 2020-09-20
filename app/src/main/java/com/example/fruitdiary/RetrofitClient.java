package com.example.fruitdiary;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.fruitdiary.Utils.BASE_URL;

public class RetrofitClient {

    private static final int timeOut = 2;
    private static Retrofit retrofitClient;
    private Gson gson = new GsonBuilder().setLenient().create();

    public static APIService getAPIService(){
        if(retrofitClient == null){
            retrofitClient = new RetrofitClient().createRetrofitClient();
        }
        return retrofitClient.create(APIService.class);
    }


    private Retrofit createRetrofitClient(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
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
