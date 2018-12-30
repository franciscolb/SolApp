package com.example.francisco.solapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WeatherWebService {
    @GET("/open-data/forecast/meteorology/cities/daily/{weather}.json")
    Call<Weather> getWeather(@Path("weather") String weather);

    
}
