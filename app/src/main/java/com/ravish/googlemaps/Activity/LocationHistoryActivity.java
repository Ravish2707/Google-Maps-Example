package com.ravish.googlemaps.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.ravish.googlemaps.Adapter.LocationHistoryAdapter;
import com.ravish.googlemaps.Database.LocationEntity;
import com.ravish.googlemaps.R;
import com.ravish.googlemaps.ViewModel.LocationViewModel;

import java.util.ArrayList;
import java.util.List;

public class LocationHistoryActivity extends AppCompatActivity {

    private RecyclerView mHistoryRecyclerview;
    private LocationHistoryAdapter adapter;
    private LocationViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_history);

        mHistoryRecyclerview = findViewById(R.id.history_recyclerview);
        mHistoryRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mHistoryRecyclerview.setHasFixedSize(true);

        mViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(LocationViewModel.class);

        mViewModel.getAllLocationHistory().observe(this, new Observer<List<LocationEntity>>() {
            @Override
            public void onChanged(List<LocationEntity> locationEntities) {
                if (locationEntities != null){
                    adapter = new LocationHistoryAdapter(locationEntities, getApplicationContext());
                    adapter.notifyDataSetChanged();
                    mHistoryRecyclerview.setAdapter(adapter);
                }
            }
        });
    }
}