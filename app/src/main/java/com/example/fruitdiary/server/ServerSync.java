package com.example.fruitdiary.server;

import com.example.fruitdiary.models.Entry;
import com.example.fruitdiary.models.Fruit;

import java.util.List;

import retrofit2.Response;

public interface ServerSync {

    void sync(Object object);

    void sync(Response response);

    void syncEntries(List<Entry> entries);
}
