package com.example.francisco.solapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class WeatherData {
    @Expose
    @SerializedName("precipitaProb")
    private String precipitaProb;

    @Expose
    @SerializedName("tMin")
    private String tMin;

    @Expose
    @SerializedName("tMax")
    private String tMax;

    @Expose
    @SerializedName("predWindDir")
    private String windDir;

    @Expose
    @SerializedName("idWeatherType")
    private String idWeatherType;

    @Expose
    @SerializedName("classWindSpeed")
    private String classWindSpeed;

    @Expose
    @SerializedName("longitude")
    private String longitude;

    @Expose
    @SerializedName("forecastDate")
    private String forecastDate;

    @Expose
    @SerializedName("latitude")
    private String latitude;

    public WeatherData(String precipitaProb, String tMin, String tMax, String windDir, String idWeatherType, String classWindSpeed, String longitude, String forecastDate, String latitude) {
        this.precipitaProb = precipitaProb;
        this.tMin = tMin;
        this.tMax = tMax;
        this.windDir = windDir;
        this.idWeatherType = idWeatherType;
        this.classWindSpeed = classWindSpeed;
        this.longitude = longitude;
        this.forecastDate = forecastDate;
        this.latitude = latitude;
    }


    public String getPrecipitaProb() {
        return precipitaProb;
    }

    public void setPrecipitaProb(String precipitaProb) {
        this.precipitaProb = precipitaProb;
    }

    public String gettMin() {
        return tMin;
    }

    public void settMin(String tMin) {
        this.tMin = tMin;
    }

    public String gettMax() {
        return tMax;
    }

    public void settMax(String tMax) {
        this.tMax = tMax;
    }

    public String getWindDir() {
        return windDir;
    }

    public void setWindDir(String windDir) {
        this.windDir = windDir;
    }

    public String getIdWeatherType() {
        return idWeatherType;
    }

    public void setIdWeatherType(String idWeatherType) {
        this.idWeatherType = idWeatherType;
    }

    public String getClassWindSpeed() {
        return classWindSpeed;
    }

    public void setClassWindSpeed(String classWindSpeed) {
        this.classWindSpeed = classWindSpeed;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getForecastDate() {
        return forecastDate;
    }

    public void setForecastDate(String forecastDate) {
        this.forecastDate = forecastDate;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
