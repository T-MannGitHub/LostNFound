package com.tmannapps.lostnfound.model;

public class User {
    private String username, description, date, location, LorF;
    private int phone, id;

    public User(int id, String username, int phone, String description, String date, String location, String LorF) {
        this.username = username;
        this.phone = phone;
        this.description = description;
        this.date = date;
        this.location = location;
        this.phone = phone;
        this.id = id;
        this.LorF = LorF;

    }

    public User(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLorF() {return LorF;}

    public void setLorF(String lorF) {this.LorF = lorF;}
}
