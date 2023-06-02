package com.tmannapps.lostnfound;

import com.google.android.gms.maps.model.LatLng;

public class Locations {

    private String description;
    private LatLng location;
    private int id;

    Locations(int id, LatLng location, String description)
    {
        this.id = id;
        this.location = location;
        this.description = description;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

