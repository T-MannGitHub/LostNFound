package com.tmannapps.lostnfound;

public class Descriptions {

    private String descriptions, LorF;
    private int id;


    Descriptions(int id, String LorF, String descriptions)
    {
        this.id = id;
        this.LorF = LorF;
        this.descriptions = descriptions;

    }

    public int getId() {
        return id;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getLorF() {return LorF; }

    public void setLorF(String LorF) { this.LorF = LorF;}
}
