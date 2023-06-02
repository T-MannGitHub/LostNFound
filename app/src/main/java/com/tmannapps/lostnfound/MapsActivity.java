package com.tmannapps.lostnfound;

import androidx.fragment.app.FragmentActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.tmannapps.lostnfound.data.DatabaseHelper;
import com.tmannapps.lostnfound.databinding.ActivityMapsItemsBinding;
import com.tmannapps.lostnfound.util.Util;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsItemsBinding binding;
    DatabaseHelper dbH;
    List<Locations> itemsListLocation = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsItemsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        dbH = new DatabaseHelper(MapsActivity.this, Util.DATABASE_NAME, null, Util.DATABASE_VERSION );
        SQLiteDatabase db = dbH.getReadableDatabase();
        Cursor cursor = db.query(Util.TABLE_NAME,
                null,null,null,null,null,null);
        while(cursor.moveToNext()){
            int indexDesc = cursor.getColumnIndex("description");
            int indexLocation = cursor.getColumnIndex("location");
            int indexID = cursor.getColumnIndex("user_id");
            String valueDesc = cursor.getString(indexDesc);//this value variable returns all descriptions.
            int valueID = cursor.getInt(indexID);
            String valueLoc = cursor.getString(indexLocation);

            for (int i=0; i <= indexID; i ++) {
                String[] latlong = valueLoc.split(",| ");
                double lat = Double.parseDouble(latlong[1]);
                double lng = Double.parseDouble(latlong[2]);
                LatLng location = new LatLng(lat, lng);
                Log.i("location lat", latlong[1]);
                Log.i("location lng", latlong[2]);

                Locations loctn = new Locations(i, location, valueDesc);
                itemsListLocation.add(loctn);
                //get location and descriptions from the database
                //add to a string array
                //for each item in array, new mMap.addMarkerposition[i].title[item.[i].getdesc
            }
            dbH.close();

            for (int i=0; i < itemsListLocation.size(); i++ )
            {
                mMap.addMarker(new MarkerOptions().position(itemsListLocation.get(i).getLocation())
                        .title(itemsListLocation.get(i).getDescription()));
            }
            LatLng darwin = new LatLng(-12.427899823604449, 130.84026393312723);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(darwin, 8));

    }
}}