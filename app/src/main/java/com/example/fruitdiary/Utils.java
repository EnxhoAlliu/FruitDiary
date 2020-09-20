package com.example.fruitdiary;

import com.example.fruitdiary.models.EntryFruitDetails;
import com.example.fruitdiary.models.Fruit;
import com.example.fruitdiary.presenters.FruitPresenter;

import java.util.List;

public class Utils {
    public static final String BASE_URL = "https://fruitdiary.test.themobilelife.com/";

    public static Fruit findFruit(EntryFruitDetails entryFruitDetails){

        for(Fruit fruit: FruitPresenter.FRUIT_LIST){
            if(entryFruitDetails.getId() == fruit.getId()){
                return fruit;
            }
        }

        return null;
    }
}
