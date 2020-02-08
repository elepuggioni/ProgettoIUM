package com.example.progettoium;

import android.media.Image;

public class City {
    private String city;
    private Image img;
    /*lista categorie??*/

    public City(){
        this.setCity("");
        this.setImg(null);
    }

    public City(String city, Image img){
        this.setCity(city);
        this.setImg(img);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }
}
