package com.example.progettoium;

import java.io.Serializable;

public class Trip implements Serializable {
    private String city;
    private String alloggio;
    //foto, categorie, amici, budget, date...

    public Trip(){
        this.setCity("");
        this.setAlloggio("");
    }

    public Trip(String city, String alloggio){
        this.setCity(city);
        this.setAlloggio(alloggio);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAlloggio() {
        return alloggio;
    }

    public void setAlloggio(String alloggio) {
        this.alloggio = alloggio;
    }

}
