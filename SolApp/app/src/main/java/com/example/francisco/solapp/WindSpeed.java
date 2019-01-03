package com.example.francisco.solapp;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity
class WindSpeed {
    @PrimaryKey
    @SerializedName("owner")
    @NonNull
    @Expose
    private String owner;

    @SerializedName("country")
    @Expose
    private String country;

    @SerializedName("data")
    @Expose
    @TypeConverters(WindSpeedConverter.class)
    private List<DataWind> data;

    public WindSpeed(@NonNull String owner, String country, List<DataWind> data) {
        this.owner = owner;
        this.country = country;
        this.data = data;
    }

    @NonNull
    public String getOwner() {
        return owner;
    }

    public void setOwner(@NonNull String owner) {
        this.owner = owner;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<DataWind> getData() {
        return data;
    }

    public void setData(List<DataWind> data) {
        this.data = data;
    }
}
