package com.example.fruitdiary.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class EntryFruitDetails implements Parcelable {
    @SerializedName("fruitId")
    private int id;
    @SerializedName("fruitType")
    private String type;
    @SerializedName("amount")
    private int amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    protected EntryFruitDetails(Parcel in) {
        id = in.readInt();
        type = in.readString();
        amount = in.readInt();
    }

    public static final Creator<EntryFruitDetails> CREATOR = new Creator<EntryFruitDetails>() {
        @Override
        public EntryFruitDetails createFromParcel(Parcel in) {
            return new EntryFruitDetails(in);
        }

        @Override
        public EntryFruitDetails[] newArray(int size) {
            return new EntryFruitDetails[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(type);
        dest.writeInt(amount);
    }
}
