package com.example.francisco.solapp;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import butterknife.BindView;
import dagger.android.AndroidInjection;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    private static String IDLOCAL = "1010500";
    private String local = "Aveiro";
    private int day = 0;
    public Map<String,Integer> locations;
    TextView dataUpdate;
    TextView forecastDate;
    TextView tempMin;
    TextView tempMax;
    TextView weatherState;
    TextView windDir;
    TextView windSpeed;
    TextView loc;
    TextView preci;
    Spinner spinner;
    Spinner spinnerDays;
    Button button;
    WeatherProfileViewModel viewModel ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dataUpdate = findViewById(R.id.dataUpdate);
        forecastDate = findViewById(R.id.forecastDate);
        tempMin = findViewById(R.id.tempMin);
        tempMax = findViewById(R.id.tempMax) ;
        weatherState = findViewById(R.id.weatherState) ;
        windDir = findViewById(R.id.windDir);
        windSpeed = findViewById(R.id.windSpeed);
        loc = findViewById(R.id.loc);
        spinner = findViewById(R.id.city);
        spinner.setOnItemSelectedListener(this);
        spinnerDays = findViewById(R.id.day);
        preci = findViewById(R.id.probPreci);
        spinnerDays.setOnItemSelectedListener(this);
        List<String> days = new ArrayList<String>();
        days.add("Hoje");
        days.add("Amanhã");
        days.add("3ºDia");
        days.add("4ºDia");
        days.add("5ºDia");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, days);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDays.setAdapter(dataAdapter);

        button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                refreshInfo();
            }
        });
        viewModel = new WeatherProfileViewModel(getApplication(),getApplicationContext());
        viewModel.init(IDLOCAL);
        viewModel.getLocations().observe(this, locations1 -> loadSpinner(locations1));
        refreshInfo();

    }

    public void loadSpinner(Locations locations1){
        List<String> locals = new ArrayList<>();
        this.locations = new HashMap<>();
        if(locations1 != null){
            for(DataLocation x : locations1.getData()){
                this.locations.put(x.getLocal(), x.getGlobalIdLocal());
                locals.add(x.getLocal());
            }
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, locals);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    public void refreshInfo(){
        viewModel.init(IDLOCAL);
        viewModel.getWeather().observe(this, weather -> updateUI(weather));
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getId() == R.id.city){
            String item = parent.getItemAtPosition(position).toString();
            local = item;
            if(String.valueOf(locations.get(item)) != IDLOCAL){
                IDLOCAL = String.valueOf(locations.get(item));
            }
        } else if (parent.getId() == R.id.day){
            day = position;
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void updateUI(@Nullable Weather weather){
        if (weather != null){
            this.dataUpdate.setText(weather.getDate());
            this.forecastDate.setText(weather.getData().get(this.day).getForecastDate());
            this.tempMin.setText(weather.getData().get(this.day).gettMin()+" ºC");
            this.tempMax.setText(weather.getData().get(this.day).gettMax()+" ºC");
            this.windDir.setText(weather.getData().get(this.day).getWindDir());
            this.preci.setText(weather.getData().get(this.day).getPrecipitaProb()+" %");
            viewModel.getWeatherType().observe(this, weatherType -> updateUI(weatherType,weather.getData().get(this.day).getIdWeatherType()));
            viewModel.getWindSpeed().observe(this, windSpeed -> updateUI(windSpeed, weather.getData().get(this.day).getClassWindSpeed()));
            //wr.getLocations().observe(this, locations -> updateUI(locations, weather.getIdLocal()));
            this.loc.setText(this.local);
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