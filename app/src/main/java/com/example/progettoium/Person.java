package com.example.progettoium;

import java.io.Serializable;

public class Person implements Serializable {
    private String username;
    private String password;
    private String citta;

    public Person(){
        this.setUsername("");
        this.setPassword("");
        this.setCitta("");
    }

    public Person(String nomeCognome, String password, String citta){
        this.setUsername(nomeCognome);
        this.setPassword(password);
        this.setCitta(citta);
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
}
