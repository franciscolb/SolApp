package com.example.francisco.solapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WeatherWebService {
    @GET("/open-data/forecast/meteorology/cities/daily/{weather}.json")
    Call<Weather> getWeather(@Path("weather") String weather);

    @GET("/open-data/weather-type-classe.json")
    Call<WeatherType> getWeatherType();

    @GET("/open-data/wind-speed-daily-classe.json")
    Call<WindSpeed> getWindSpeed();

    @GET("https://api.ipma.pt/open-data/distrits-islands.json")
    Call<Locations> getLocations();
}
