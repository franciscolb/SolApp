package com.example.francisco.solapp;

import android.arch.lifecycle.LiveData;
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

    private static int FRESH_TIMEOUT_IN_MINUTES = 5;

    private final WeatherWebService webService;
    private final WeatherDao weatherDao;
    private final Executor executor;

    @Inject

    public WeatherRepository(WeatherWebService webService, WeatherDao weatherDao, Executor executor) {
        this.webService = webService;
        this.weatherDao = weatherDao;
        this.executor = executor;
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
                        Toast.makeText(App.context, "Data refreshed", Toast.LENGTH_LONG).show();
                        executor.execute(() -> {
                            Weather weather = response.body();
                            weather.setLastRefresh(new Date());
                            weatherDao.save(weather);
                            Log.d("Repo",weather.getCountry());
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
}
