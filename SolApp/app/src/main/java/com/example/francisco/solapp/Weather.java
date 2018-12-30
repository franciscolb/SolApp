package com.example.francisco.solapp;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

@Entity
public class Weather {

    @PrimaryKey
    @NonNull
    @SerializedName("globalIdLocal")
    @Expose
    private int idLocal;

    @Expose
    @SerializedName("dataUpdate")
    private String date;

    @SerializedName("owner")
    @Expose
    private String owner;

    @SerializedName("country")
    @Expose
    private String country;

    @SerializedName("data")
    @Expose
    @TypeConverters(DaysForecastTypeConverter.class)
    private List<Data> data;

    private Date lastRefresh;

    public Weather(){ }


    public Weather(@NonNull String date, String owner, String country, int idLocal, List<Data> data, Date lastRefresh) {
        this.date = date;
        this.owner = owner;
        this.country = country;
        this.idLocal = idLocal;
        this.data = data;
        this.lastRefresh = lastRefresh;
    }

    @NonNull
    public String getDate() {
        return date;
    }

    public void setDate(@NonNull String date) {
        this.date = date;
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

    public int getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }

    public Date getLastRefresh() {
        return lastRefresh;
    }

    public void setLastRefresh(Date lastRefresh) {
        this.lastRefresh = lastRefresh;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
}
