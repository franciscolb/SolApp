package com.example.francisco.solapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataWeather {

    @SerializedName("descIdWeatherTypeEN")
    @Expose
    private String weatherTypeEN;

    @SerializedName("descIdWeatherTypePT")
    @Expose
    private String weatherTypePT;

    @SerializedName("idWeatherType")
    @Expose
    private String weatherTypeId;

    public DataWeather(String weatherTypeEN, String weatherTypePT, String weatherTypeId) {
        this.weatherTypeEN = weatherTypeEN;
        this.weatherTypePT = weatherTypePT;
        this.weatherTypeId = weatherTypeId;
    }

    public String getWeatherTypeEN() {
        return weatherTypeEN;
    }

    public void setWeatherTypeEN(String weatherTypeEN) {
        this.weatherTypeEN = weatherTypeEN;
    }

    public String getWeatherTypePT() {
        return weatherTypePT;
    }

    public void setWeatherTypePT(String weatherTypePT) {
        this.weatherTypePT = weatherTypePT;
    }

    public String getWeatherTypeId() {
        return weatherTypeId;
    }

    public void setWeatherTypeId(String weatherTypeId) {
        this.weatherTypeId = weatherTypeId;
    }
}
