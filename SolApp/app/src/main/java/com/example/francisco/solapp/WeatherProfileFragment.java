package com.example.francisco.solapp;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import javax.inject.Inject;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;



public class WeatherProfileFragment extends Fragment {
    // FOR DATA
    public static final String GLOBALIDLOCAL = "ID";

    private WeatherProfileViewModel viewModel;
    private Application aplication;

    // FOR DESIGN
    @BindView(R.id.dataUpdate) TextView dataUpdate;
    @BindView(R.id.forecastDate) TextView forecastDate;
    @BindView(R.id.tempMin) TextView tempMin;
    @BindView(R.id.tempMax) TextView tempMax;
    @BindView(R.id.weatherState) TextView weatherState;
    @BindView(R.id.windDir) TextView windDir;
    @BindView(R.id.windSpeed) TextView windSpeed;
    @BindView(R.id.loc) TextView loc;

    public WeatherProfileFragment() { }
/*
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //View view = inflater.inflate(R.layout.fragment_weather_profile, container, false);
        return view;
    }*/

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.configureViewModel();
    }

    // -----------------
    // CONFIGURATION
    // -----------------


    private void configureViewModel(){
        String idLocal = getArguments().getString(GLOBALIDLOCAL);
        //AppModule app = new AppModule();
        //viewModel = WeatherProfileViewModel();
        viewModel.init(idLocal);
        viewModel.getWeather().observe(this, weather -> updateUI(weather));

    }

    // -----------------
    // UPDATE UI
    // -----------------

    private void updateUI(@Nullable Weather weather){
        if (weather != null){
            this.dataUpdate.setText(weather.getDate());
            this.forecastDate.setText(weather.getData().get(0).getForecastDate());
            this.tempMin.setText(weather.getData().get(0).gettMin()+" ºC");
            this.tempMax.setText(weather.getData().get(0).gettMax()+" ºC");
            this.windDir.setText(weather.getData().get(0).getWindDir());
            viewModel.getWeatherType().observe(this, weatherType -> updateUI(weatherType,weather.getData().get(0).getIdWeatherType()));
            viewModel.getWindSpeed().observe(this, windSpeed -> updateUI(windSpeed, weather.getData().get(0).getClassWindSpeed()));
            viewModel.getLocations().observe(this, locations -> updateUI(locations, weather.getIdLocal()));
        }
    }

    private void updateUI(Locations locations, int globalIdLocal) {
        if(locations != null){
            for(DataLocation x : locations.getData()){
                if(x.getGlobalIdLocal() == globalIdLocal){
                    this.loc.setText(x.getLocal());
                }
            }
        }
    }

    private void updateUI(@Nullable WeatherType weatherType, String id){
        if (weatherType != null){
            weatherState.setText(weatherType.getData().get(Integer.parseInt(id)+1).getWeatherTypePT());

        }
    }

    private void updateUI(@Nullable WindSpeed windSpeed, String id){
        if (windSpeed != null){
            this.windSpeed.setText(windSpeed.getData().get(Integer.parseInt(id)+1).getWindSpeedPT());

        }
    }


}
