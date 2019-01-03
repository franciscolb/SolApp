package com.example.francisco.solapp;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.google.gson.Gson;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherProfileViewModel extends ViewModel {
    private LiveData<Weather> weather;
    private WeatherRepository weatherRepo;
    //connect weatherType
    private LiveData<WeatherType> weatherType;
    //connect windSpeed
    private LiveData<WindSpeed> windSpeed;
    //locations
    private LiveData<Locations> locations;
    private Application application;


    public WeatherProfileViewModel(Application aplication, Context context) {
        this.application = aplication;
        MyDatabase db = getDB();
        WeatherDao wdao = provideUserDao(db);
        Executor ex = provideExecutor();
        Retrofit r = provideRetrofit(new Gson());
        WeatherWebService wb = provideApiWebservice(r);
        weatherRepo = provideUserRepository(wb,wdao,ex,context);
    }


    public void init(String dataUpdate) {
        /*if (this.weather != null) {
            return;
        } else if(this.weatherType != null){
            return;
        } else if(this.windSpeed != null){
            return;
        }else*/ if(this.locations == null){
            locations = weatherRepo.getLocations();
        }
        weather = weatherRepo.getWeather(dataUpdate);
        weatherType = weatherRepo.getWeatherType();
        windSpeed = weatherRepo.getWindSpeed();
        //locations = weatherRepo.getLocations();
    }

    public LiveData<Weather> getWeather() {
        return this.weather;
    }

    public LiveData<WeatherType> getWeatherType() { return this.weatherType; }

    public LiveData<WindSpeed> getWindSpeed() {return this.windSpeed; }

    public LiveData<Locations> getLocations() {return this.locations; }

    private MyDatabase getDB(){
        return Room.databaseBuilder(this.application,
                MyDatabase.class, "MyDatabase.db")
                .build();
    }

    private WeatherDao provideUserDao(MyDatabase database) { return database.weatherDao(); }

    private Executor provideExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    private WeatherRepository provideUserRepository(WeatherWebService webservice, WeatherDao userDao, Executor executor, Context context) {
        return new WeatherRepository(webservice, userDao, executor,context);
    }

    private Retrofit provideRetrofit(Gson gson) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("https://api.ipma.pt")
                .build();
        return retrofit;
    }

    private WeatherWebService provideApiWebservice(Retrofit restAdapter) {
        return restAdapter.create(WeatherWebService.class);
    }
}
