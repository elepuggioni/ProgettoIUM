package com.example.progettoium;

import java.io.Serializable;

public class Person implements Serializable {
    private String nomeCognome;
    private String password;
    private String citta;

    public Person(){
        this.setNomeCognome("");
        this.setPassword("");
        this.setCitta("");
    }

    public Person(String nomeCognome, String password, String citta){
        this.setNomeCognome(nomeCognome);
        this.setPassword(password);
        this.setCitta(citta);
    }

    public String getNomeCognome() {
        return nomeCognome;
    }

    public void setNomeCognome(String nomeCognome) {
        this.nomeCognome = nomeCognome;
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
}
