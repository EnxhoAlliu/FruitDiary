package com.example.fruitdiary.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Entry implements Parcelable {

    @SerializedName("id")
    private int id;
    @SerializedName("date")
    private String date;
    @SerializedName("fruit")
    private List<EntryFruitDetails> fruitList;

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public List<EntryFruitDetails> getFruitList() {
        return fruitList;
    }


    protected Entry(Parcel in) {
        id = in.readInt();
        date = in.readString();
        fruitList = in.createTypedArrayList(EntryFruitDetails.CREATOR);
    }

    public static final Creator<Entry> CREATOR = new Creator<Entry>() {
        @Override
        public Entry createFromParcel(Parcel in) {
            return new Entry(in);
        }

        @Override
        public Entry[] newArray(int size) {
            return new Entry[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(date);
        dest.writeTypedList(fruitList);
    }
}

