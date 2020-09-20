package com.example.fruitdiary;

import com.example.fruitdiary.models.Entry;
import com.example.fruitdiary.models.Fruit;

import java.util.List;

import retrofit2.Response;

public interface APISync {

    void sync(Response response);

    void syncFruits(List<Fruit> fruits);

    void syncEntries(List<Entry> entries);
}
