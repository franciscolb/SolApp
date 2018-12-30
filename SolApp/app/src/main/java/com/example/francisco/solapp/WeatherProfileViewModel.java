package com.example.francisco.solapp;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

public class WeatherProfileViewModel extends ViewModel {
    private LiveData<Weather> weather;
    private WeatherRepository weatherRepo;

    @Inject
    public WeatherProfileViewModel(WeatherRepository weatherRepo) {
        this.weatherRepo = weatherRepo;
    }


    public void init(String dataUpdate) {
        if (this.weather != null) {
            return;
        }
        weather = weatherRepo.getWeather(dataUpdate);
    }

    public LiveData<Weather> getWeather() {
        return this.weather;
    }
}
