package com.example.progettoium;

import android.view.View;
import android.widget.ImageView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Person implements Serializable {
    private String username;
    private String password;
    private String citta;
    private String bio;
    private List<Trip> viaggi = new ArrayList<>();

    public Person(){
        this.setUsername("");
        this.setPassword("");
        this.setCitta("");
        this.setBio("");
    }

    public Person(String nomeCognome, String password, String citta,String bio){
        this.setUsername(nomeCognome);
        this.setPassword(password);
        this.setCitta(citta);
        this.setBio(bio);
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<Trip> getViaggi() {
        return viaggi;
    }

    public void setViaggi(List<Trip> viaggi) {
        this.viaggi = viaggi;
    }

}
