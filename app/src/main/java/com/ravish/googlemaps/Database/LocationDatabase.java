package com.ravish.googlemaps.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = LocationEntity.class, version = 1, exportSchema = false)
public abstract class LocationDatabase extends RoomDatabase {

    private static  LocationDatabase mInstance;
    public abstract LocationDao locationDao();

    public static final ExecutorService dataExecutors = Executors.newSingleThreadExecutor();

    public static LocationDatabase getInstance(Context context){
        if (mInstance == null){
            synchronized (LocationDatabase.class){
                if (mInstance == null){
                   mInstance = Room.databaseBuilder(context.getApplicationContext(), LocationDatabase.class, "location_db").build();

                }
            }
        }
        return mInstance;
    }

}
