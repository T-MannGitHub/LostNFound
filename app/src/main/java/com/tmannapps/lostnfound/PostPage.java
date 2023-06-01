package com.tmannapps.lostnfound;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.tmannapps.lostnfound.data.DatabaseHelper;
import com.tmannapps.lostnfound.databinding.ActivityPostPageBinding;
import com.tmannapps.lostnfound.model.User;
import com.tmannapps.lostnfound.util.Util;

public class PostPage extends AppCompatActivity {
    private ActivityPostPageBinding postPageBinding;
    DatabaseHelper db;
    String lostOrFound = "";
    public static int numItems = 0;
    LocationManager locationManager;
    LocationListener locationListener;


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    if (ActivityCompat.checkSelfPermission(PostPage.this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED)
                    {
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                    }
                }
            }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_post_page);
        postPageBinding = ActivityPostPageBinding.inflate(getLayoutInflater());
        View view = postPageBinding.getRoot();
        setContentView(postPageBinding.getRoot());
        db = new DatabaseHelper(this, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);


        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                postPageBinding.editTextLocation.setText(location.toString());
            }
        };

        postPageBinding.buttonGetLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


        if (ActivityCompat.checkSelfPermission(PostPage.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(PostPage.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(PostPage.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }
            }
        });
        postPageBinding.radioButtonLost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    postPageBinding.radioButtonFound.setChecked(false);
                    lostOrFound = "Lost: ";
                }});

        postPageBinding.radioButtonFound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postPageBinding.radioButtonLost.setChecked(false);
                lostOrFound = "Found: ";
            }});

    postPageBinding.floatingActionButtonSave.setOnClickListener(view2 -> {
        int id = 0;
        String username = postPageBinding.editTextName.getText().toString();
        int phone = postPageBinding.editTextPhone.getInputType();
        String description = postPageBinding.editTextDescription.getText().toString();
        String date = postPageBinding.editTextDate.getText().toString();
        String location = postPageBinding.editTextLocation.getText().toString();
        String LorF = lostOrFound;
        numItems +=1;

        long result = db.insertUser(new User(id,username, phone, description, date, location, LorF));
        if (result > 0)
        {
            Intent intent = new Intent(PostPage.this, AllItems.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(PostPage.this, "Item registration failed. Try again.", Toast.LENGTH_SHORT).show();
        }
        if (lostOrFound == "Lost: ")
        {
            postPageBinding.radioButtonLost.setChecked(false);
        } else
        {
            postPageBinding.radioButtonFound.setChecked(false);
            lostOrFound = "";
        }
    });
    }
}