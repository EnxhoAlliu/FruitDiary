package com.example.fruitdiary;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Fruit  implements Parcelable {
    @SerializedName("id")
    private int id;
    @SerializedName("vitamins")
    private int vitamins;
    @SerializedName("type")
    private String type;
    @SerializedName("image")
    private String imageRelativePath;


    protected Fruit(Parcel in) {
        id = in.readInt();
        vitamins = in.readInt();
        type = in.readString();
        imageRelativePath = in.readString();
    }

    public static final Creator<Fruit> CREATOR = new Creator<Fruit>() {
        @Override
        public Fruit createFromParcel(Parcel in) {
            return new Fruit(in);
        }

        @Override
        public Fruit[] newArray(int size) {
            return new Fruit[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(vitamins);
        parcel.writeString(type);
        parcel.writeString(imageRelativePath);
    }
}
