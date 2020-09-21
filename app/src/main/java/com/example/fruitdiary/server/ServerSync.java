package com.example.fruitdiary.server;

import com.example.fruitdiary.models.Entry;

import java.util.List;

public interface ServerSync {

    void sync(Object object, int requestCode);

    void syncEntries(List<Entry> entries);
}
