package com.example.francisco.solapp;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.Date;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface WeatherDao {

    @Insert(onConflict = REPLACE)
    void save(Weather weather);

    @Query("SELECT * FROM weather WHERE idLocal = :dataUpdate")
    LiveData<Weather> load(String dataUpdate);

    @Query("SELECT * FROM weather WHERE idLocal = :dataUpdate AND lastRefresh > :lastRefreshMax LIMIT 1")
    Weather hasWeather(String dataUpdate, Date lastRefreshMax);


    // to connect id weather type to description
    @Insert(onConflict = REPLACE)
    void save(WeatherType weatherType);

    @Query("SELECT * FROM weatherType")
    LiveData<WeatherType> loadWeatherType();

    //wind speed
    @Insert(onConflict = REPLACE)
    void save(WindSpeed windSpeed);

    @Query("SELECT * FROM windSpeed")
    LiveData<WindSpeed> loadWindSpeed();

    //locations
    @Insert(onConflict = REPLACE)
    void save(Locations locations);

    @Query("SELECT * FROM locations")
    LiveData<Locations> loadLocations();

}
