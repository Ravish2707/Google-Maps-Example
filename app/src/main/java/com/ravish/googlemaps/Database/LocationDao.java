package com.ravish.googlemaps.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LocationDao {

    @Insert
    void insert(LocationEntity entity);

    @Delete
    void delete(LocationEntity entity);

    @Query("DELETE FROM location_table")
    void deleteAllHistory();

    @Query("SELECT * FROM location_table")
    LiveData<List<LocationEntity>> getAllLocationHistory();
}
