package com.ravish.googlemaps.Repository;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.ravish.googlemaps.Database.LocationDao;
import com.ravish.googlemaps.Database.LocationDatabase;
import com.ravish.googlemaps.Database.LocationEntity;

import java.util.List;

public class LocationRepository {

    private LocationDao locationDao;
    private LiveData<List<LocationEntity>> allLocationHistory;

    public LocationRepository(@NonNull Application application){
        LocationDatabase database = LocationDatabase.getInstance(application.getApplicationContext());
        locationDao = database.locationDao();
        allLocationHistory = locationDao.getAllLocationHistory();
    }

    public void Insert(LocationEntity entity){
        LocationDatabase.dataExecutors.execute(()->{
            locationDao.insert(entity);
        });
    }

    public void delete(LocationEntity entity){
        LocationDatabase.dataExecutors.execute(()->{
            locationDao.delete(entity);
        });
    }

    public void deleteAllLocationHistory(){
        LocationDatabase.dataExecutors.execute(()->{
            locationDao.deleteAllHistory();
        });
    }

    public LiveData<List<LocationEntity>> getGetAllLocationHistory(){
        return allLocationHistory;
    }
}
