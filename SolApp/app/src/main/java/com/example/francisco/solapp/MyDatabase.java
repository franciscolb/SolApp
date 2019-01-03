package com.example.francisco.solapp;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

@Database(entities = {Weather.class,WeatherType.class,WindSpeed.class,Locations.class}, version = 1)
@TypeConverters(DateConverter.class)
public abstract class MyDatabase extends RoomDatabase {

    private static volatile MyDatabase INSTANCE;


    public abstract WeatherDao weatherDao();
}
