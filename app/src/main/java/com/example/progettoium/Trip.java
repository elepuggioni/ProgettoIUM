package com.example.progettoium;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Trip implements Serializable {
    private String city;
    private String departure_city;
    private String alloggio;
    private Integer budget;
    private List<String> arte = new ArrayList<>();
    private List<String> sport = new ArrayList<>();
    private List<String> shopping = new ArrayList<>();
    private List<String> ristoranti = new ArrayList<>();
    private List<String> amici = new ArrayList<>();
    private Calendar partenza;
    private Calendar ritorno;

    public Trip(){
        this.setCity("Milano MI, Italia");  //Città di default
        this.setDeparture_city("Cagliari CA, Italia");
        this.setAlloggio("");
        this.setBudget(0);
        this.setPartenza(null);
        this.setRitorno(null);
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

    public List<String> getArte() {
        return arte;
    }

    public void setArte(List<String> arte) {
        this.arte = arte;
    }

    public List<String> getSport() {
        return sport;
    }

    public void setSport(List<String> sport) {
        this.sport = sport;
    }

    public List<String> getShopping() {
        return shopping;
    }

    public void setShopping(List<String> shopping) {
        this.shopping = shopping;
    }

    public List<String> getRistoranti() {
        return ristoranti;
    }

    public void setRistoranti(List<String> ristoranti) {
        this.ristoranti = ristoranti;
    }

    public List<String> getAmici() { return amici; }

    public void setAmici(List<String> amici) { this.amici = amici; }

    public Calendar getPartenza() {
        return partenza;
    }

    public void setPartenza(Calendar partenza) {
        this.partenza = partenza;
    }

    public Calendar getRitorno() {
        return ritorno;
    }

    public void setRitorno(Calendar ritorno) {
        this.ritorno = ritorno;
    }

    public String getDeparture_city() {
        return departure_city;
    }

    public void setDeparture_city(String departure_city) {
        this.departure_city = departure_city;
    }
}
