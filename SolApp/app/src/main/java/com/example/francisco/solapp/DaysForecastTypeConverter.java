package com.example.francisco.solapp;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class DaysForecastTypeConverter {

    static Gson gson = new Gson();

    @TypeConverter
    public static List<WeatherData> stringToList(String data){
        if (data == null){
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<WeatherData>>() {}.getType();

        return gson.fromJson(data,listType);
    }

    @TypeConverter
    public static String listToString(List<WeatherData> data){
        return gson.toJson(data);
    }
}
