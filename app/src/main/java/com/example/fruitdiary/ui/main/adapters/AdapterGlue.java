package com.example.fruitdiary.ui.main.adapters;

import com.example.fruitdiary.models.Entry;
import com.example.fruitdiary.models.Fruit;

public interface AdapterGlue {

    void attachEntry(Entry entry);

    void attachFruit(Fruit fruit);
}
