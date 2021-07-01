package com.ravish.googlemaps.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ravish.googlemaps.Database.LocationEntity;
import com.ravish.googlemaps.Repository.LocationRepository;

import java.util.List;

public class LocationViewModel extends AndroidViewModel {

    private LocationRepository repository;
    private LiveData<List<LocationEntity>> allLocationHistory;

    public LocationViewModel(@NonNull Application application) {
        super(application);

        repository = new LocationRepository(application);
        allLocationHistory = repository.getGetAllLocationHistory();
    }

    public void Insert(LocationEntity entity){
        repository.Insert(entity);
    }

    public void delete(LocationEntity entity){
        repository.delete(entity);
    }

    public void deleteAllLocationHistory(){
        repository.deleteAllLocationHistory();
    }

    public LiveData<List<LocationEntity>> getAllLocationHistory(){
        return allLocationHistory;
    }
}
