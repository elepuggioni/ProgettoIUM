package com.example.progettoium;

import android.media.Image;

public class City {
    private String city;
    private String state;
    private Image img;
    /*lista categorie??*/

    public City(){
        this.setCity("");
        this.setState("");
        this.setImg(null);
    }

    public City(String city, String state, Image img){
        this.setCity(city);
        this.setState(state);
        this.setImg(img);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }
}
