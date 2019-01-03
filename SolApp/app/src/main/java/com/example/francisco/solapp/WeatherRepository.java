package com.example.francisco.solapp;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.MainThread;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class WeatherRepository {

    private static int FRESH_TIMEOUT_IN_MINUTES = 15;

    private final WeatherWebService webService;
    private final WeatherDao weatherDao;
    private final Executor executor;
    private Context context;



    public WeatherRepository(WeatherWebService webService, WeatherDao weatherDao, Executor executor, Context context) {
        this.webService = webService;
        this.weatherDao = weatherDao;
        this.executor = executor;
        this.context = context;
    }

    public LiveData<Weather> getWeather(String dataUpdate){
        refreshWeather(dataUpdate);
        return weatherDao.load(dataUpdate);
    }

    private void refreshWeather(final String dataUpdate){
        executor.execute(() -> {
            boolean weatherExists = (weatherDao.hasWeather(dataUpdate, getMaxRefreshTime(new Date())) != null);
            if(!weatherExists){
                webService.getWeather(dataUpdate).enqueue(new Callback<Weather>() {
                    @Override
                    public void onResponse(Call<Weather> call, Response<Weather> response) {
                        Toast.makeText(context, "WeatherData refreshed", Toast.LENGTH_SHORT).show();
                        executor.execute(() -> {
                            Weather weather = response.body();
                            if(weather != null) {
                                weather.setLastRefresh(new Date());
                                weatherDao.save(weather);
                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<Weather> call, Throwable t) {

                    }
                });
            }

        });
    }

    private Date getMaxRefreshTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, -FRESH_TIMEOUT_IN_MINUTES);
        return cal.getTime();
    }

    // connect weather Type
    public void loadWeatherType(){
        executor.execute(() -> {
                webService.getWeatherType().enqueue(new Callback<WeatherType>() {
                    @Override
                    public void onResponse(Call<WeatherType> call, Response<WeatherType> response) {
                        executor.execute(() -> {
                            WeatherType weatherType = response.body();
                            weatherDao.save(weatherType);
                        });
                    }

                    @Override
                    public void onFailure(Call<WeatherType> call, Throwable t) {
                        //Toast.makeText(App.context, "LOAD WEATHER TYPE FAILED", Toast.LENGTH_LONG).show();
                    }
                });


        });
    }

    public LiveData<WeatherType> getWeatherType(){
        loadWeatherType();
        return weatherDao.loadWeatherType();
    }

    //wind speed

    public void loadWindSpeed(){
        executor.execute(() -> {
            webService.getWindSpeed().enqueue(new Callback<WindSpeed>() {
                @Override
                public void onResponse(Call<WindSpeed> call, Response<WindSpeed> response) {
                    executor.execute(() -> {
                        WindSpeed windSpeed = response.body();
                        weatherDao.save(windSpeed);
                    });
                }

                @Override
                public void onFailure(Call<WindSpeed> call, Throwable t) {
                   // Toast.makeText(App.context, "LOAD WIND SPEED FAILED", Toast.LENGTH_LONG).show();
                }
            });


        });
    }

    public LiveData<WindSpeed> getWindSpeed(){
        loadWindSpeed();
        return weatherDao.loadWindSpeed();
    }

    //location
    public void loadLocations(){
        executor.execute(() -> {
            webService.getLocations().enqueue(new Callback<Locations>() {
                @Override
                public void onResponse(Call<Locations> call, Response<Locations> response) {
                    executor.execute(() -> {
                        Locations locations = response.body();
                        weatherDao.save(locations);
                    });
                }

                @Override
                public void onFailure(Call<Locations> call, Throwable t) {
                    //Toast.makeText(App.context, "LOAD WIND SPEED FAILED", Toast.LENGTH_LONG).show();
                }
            });


        });
    }

    public LiveData<Locations> getLocations(){
        loadLocations();
        return weatherDao.loadLocations();
    }


}

