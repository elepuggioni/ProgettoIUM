package com.example.progettoium;

import android.media.Image;

import java.io.Serializable;

public class City implements Serializable {
    private String city;

    /*lista categorie??*/

    public City(){
        this.setCity("");
    }

    public City(String city, Image img){
        this.setCity(city);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
