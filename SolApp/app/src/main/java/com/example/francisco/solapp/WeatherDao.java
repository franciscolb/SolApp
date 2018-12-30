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


}
