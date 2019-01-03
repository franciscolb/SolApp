package com.example.francisco.solapp;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity
class Locations {
    @PrimaryKey
    @NonNull
    @SerializedName("owner")
    @Expose
    private String owner;

    @SerializedName("country")
    @Expose
    private String country;

    @SerializedName("data")
    @Expose
    @TypeConverters(DataLocationConverter.class)
    private List<DataLocation> data;

    public Locations(String owner, String country, List<DataLocation> data) {
        this.owner = owner;
        this.country = country;
        this.data = data;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<DataLocation> getData() {
        return data;
    }

    public void setData(List<DataLocation> data) {
        this.data = data;
    }
}
