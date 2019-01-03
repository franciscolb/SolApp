package com.example.francisco.solapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataWind {
    @SerializedName("descClassWindSpeedDailyEN")
    @Expose
    private String windSpeedEN;

    @SerializedName("descClassWindSpeedDailyPT")
    @Expose
    private String windSpeedPT;

    @SerializedName("classWindSpeed")
    @Expose
    private String windSpeedId;

    public DataWind(String windSpeedEN, String windSpeedPT, String windSpeedId) {
        this.windSpeedEN = windSpeedEN;
        this.windSpeedPT = windSpeedPT;
        this.windSpeedId = windSpeedId;
    }

    public String getWindSpeedEN() {
        return windSpeedEN;
    }

    public void setWindSpeedEN(String windSpeedEN) {
        this.windSpeedEN = windSpeedEN;
    }

    public String getWindSpeedPT() {
        return windSpeedPT;
    }

    public void setWindSpeedPT(String windSpeedPT) {
        this.windSpeedPT = windSpeedPT;
    }

    public String getWindSpeedId() {
        return windSpeedId;
    }

    public void setWindSpeedId(String windSpeedId) {
        this.windSpeedId = windSpeedId;
    }
}
