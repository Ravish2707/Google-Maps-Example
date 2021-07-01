package com.ravish.googlemaps.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ravish.googlemaps.R;

public class LocationsDetailActivity extends AppCompatActivity {

    private String latitude;
    private String longitude;

    private TextView mLatitudeTextView;
    private TextView mLongitudeTextView;
    private Button checkHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations_detail);

        latitude = getIntent().getStringExtra(MainActivity.LATITUDE);
        longitude = getIntent().getStringExtra(MainActivity.LATITUDE);

        mLatitudeTextView = findViewById(R.id.tv_latitude);
        mLongitudeTextView = findViewById(R.id.tv_longitude);
        checkHistory = findViewById(R.id.btn_history);

        mLatitudeTextView.setText(String.format("Latitude: %s", latitude));
        mLongitudeTextView.setText(String.format("Longitude %s", longitude));

        checkHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LocationsDetailActivity.this, LocationHistoryActivity.class);
                startActivity(intent);
            }
        });
    }
}