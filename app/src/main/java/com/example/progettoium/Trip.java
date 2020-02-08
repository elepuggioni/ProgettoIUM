package com.example.progettoium;

import java.io.Serializable;

public class Trip implements Serializable {
    private String city;
    private String alloggio;
    private Integer budget;
    //foto, categorie, amici, date...

    public Trip(){
        this.setCity("");
        this.setAlloggio("");
        this.setBudget(0);
    }

    public Trip(String city, String alloggio, Integer budget){
        this.setCity(city);
        this.setAlloggio(alloggio);
        this.setBudget(budget);
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

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }
}
