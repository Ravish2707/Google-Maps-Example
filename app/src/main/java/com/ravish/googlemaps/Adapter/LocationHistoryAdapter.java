package com.ravish.googlemaps.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.ravish.googlemaps.Database.LocationEntity;
import com.ravish.googlemaps.R;
import com.ravish.googlemaps.ViewModel.LocationViewModel;

import java.util.List;

public class LocationHistoryAdapter extends RecyclerView.Adapter<LocationHistoryAdapter.LocationHistoryViewHolder>{

    private List<LocationEntity> locationEntities;
    private Context mContext;
    private LocationViewModel mViewModel;

    public LocationHistoryAdapter(List<LocationEntity> locationEntities, Context mContext) {
        this.locationEntities = locationEntities;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public LocationHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.single_history_view, parent, false);
        return new LocationHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationHistoryViewHolder holder, int position) {
        LocationEntity entity = locationEntities.get(position);
        holder.mLocationDetails.setText(String.format("Location Added at - %s", entity.getDate()));
        holder.mLatitude.setText(String.format("Latitude: %s", entity.getLatitude()));
        holder.mLongitude.setText(String.format("Longitude: %s", entity.getLongitude()));

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(mContext).setTitle("Delete Location History").setMessage("Do You want to delete history?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mViewModel = new ViewModelProvider((ViewModelStoreOwner) mContext).get(LocationViewModel.class);
                                mViewModel.delete(entity);
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return locationEntities.size();
    }

    public class LocationHistoryViewHolder extends RecyclerView.ViewHolder {

        private TextView mLocationDetails;
        private TextView mLatitude;
        private TextView mLongitude;

        public LocationHistoryViewHolder(@NonNull View itemView) {
            super(itemView);

            mLocationDetails = itemView.findViewById(R.id.tv_location_detail);
            mLatitude = itemView.findViewById(R.id.tv_latitude_loc);
            mLongitude = itemView.findViewById(R.id.tv_longitude_loc);
        }
    }
}
