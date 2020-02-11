package com.example.progettoium;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FindAttractions extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    TextView title, subtitle1;
    Trip trip;
    int categoria;
    Button confirm, removeTop3;
    CheckBox cb1, cb2, cb3;
    LinearLayout top3, checkboxes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_attractions);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_category);
        mapFragment.getMapAsync(this);

        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(Home.TRIP);

        if(obj instanceof Trip){
            trip = (Trip) obj;
        }else {
            trip = new Trip();
        }

        title = findViewById(R.id.attraction_title);
        title.setText(trip.getCity());

        int obj2 = intent.getIntExtra(Activities.CATEGORIA,0);
        if (obj2!= 0){
            categoria = obj2;
        }

        checkboxes = findViewById(R.id.attraction_checkboxes);
        cb1 = findViewById(R.id.cb1);
        cb2 = findViewById(R.id.cb2);
        cb3 = findViewById(R.id.cb3);
        top3 = findViewById(R.id.attraction_top3);
        removeTop3 = findViewById(R.id.attraction_remove_top3);
        removeTop3.setTag(1);

        subtitle1 =findViewById(R.id.attraction_subtitle1);
        switch (categoria){
            case 1: subtitle1.setText("ARTE");
            break;
            case 2: subtitle1.setText("SPORT");
            break;
            case 3: subtitle1.setText("SHOPPING");
            break;
            case 4: subtitle1.setText("RISTORANTI");
            break;
            default: subtitle1.setText("CATEGORIA NON TROVATA");
        }


        confirm = findViewById(R.id.attraction_confirm);
        /*confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });*/

        removeTop3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(removeTop3.getTag()==(Object) 1) {   //Si vedono ancora le checkbox
                    top3.removeView(checkboxes);
                    removeTop3.setTag(2);
                    removeTop3.setBackgroundResource(R.drawable.ic_add);
                }else{
                    top3.addView(checkboxes);
                    removeTop3.setTag(1);
                    removeTop3.setBackgroundResource(R.drawable.ic_remove);
                }
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        Geocoder geocoder = new Geocoder(FindAttractions.this);
        List<Address> list = new ArrayList<>();
        try {
            list = geocoder.getFromLocationName(trip.getCity(),1);
        }catch (IOException e){};

        if(list.size()>0){
            Address address = list.get(0);
            LatLng citta = new LatLng(address.getLatitude(),address.getLongitude());
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(citta, 13F));

            //Mostra le diverse attrazioni
            showAttractions(categoria, trip.getCity());
        }
    }

    public MarkerOptions findAttraction(String nome){
        Geocoder geocoder = new Geocoder(FindAttractions.this);

        List<Address> list = new ArrayList<>();
        try {
            list = geocoder.getFromLocationName(nome,1);
        }catch (IOException e){};

        if(list.size()>0){
            Address address = list.get(0);
            LatLng attrazione = new LatLng(address.getLatitude(),address.getLongitude());
            MarkerOptions markerOptions = new MarkerOptions().position(attrazione);
            return markerOptions;
        }
        return null;
    }


    private void showAttractions(final int categoria, String citta){
        Marker m1,m2,m3;

        switch (categoria){
            case 1: //ARTE
                if (citta.equals("Milano MI, Italia")){
                     m1 = mMap.addMarker(findAttraction("Duomo di Milano").title("Il Duomo"));
                     m2 = mMap.addMarker(findAttraction("Castello Sforzesco").title("Castello Sforzesco"));
                     m3 = mMap.addMarker(findAttraction("Teatro alla Scala").title("Teatro alla Scala"));
                     cb1.setText("prova 1");
                     cb2.setText("prova 2");
                     cb3.setText("prova 3");
                }else if(citta.equals("Roma RM, Italia")){
                     m1 = mMap.addMarker(findAttraction("Colosseo").title("Il Colosseo"));
                     m2 = mMap.addMarker(findAttraction("Patheon Roma").title("Il Pantheon"));
                     m3 = mMap.addMarker(findAttraction("Fontana di Trevi").title("Fontana di Trevi"));
                }
                break;
            case 2: //SPORT
                if (citta.equals("Milano MI, Italia")){
                     m1 = mMap.addMarker(findAttraction("Stadio Meazza").title("Stadio Meazza"));
                     m2 = mMap.addMarker(findAttraction("Inter Store Milano").title("Inter Store"));
                     m3 = mMap.addMarker(findAttraction("Milan Store Milano").title("Milan Store"));
                }else if(citta.equals("Roma RM, Italia")){
                     m1 = mMap.addMarker(findAttraction("Stadio Olimpico Roma").title("Stadio Olimpico"));
                     m2 = mMap.addMarker(findAttraction("AS Roma Store Roma").title("Roma Store"));
                     m3 = mMap.addMarker(findAttraction("Sport Center store roma").title("Sport Center Store"));
                }
                break;
            case 3: //SHOPPING
                if (citta.equals("Milano MI, Italia")){
                     m1 = mMap.addMarker(findAttraction("Galleria Vittorio Emanuele Milano").title("Galleria Vittorio Emanuele"));
                     m2 = mMap.addMarker(findAttraction("The Merchant Of Venice - Milano Boutique").title("The Merchant Of Venice"));
                     m3 = mMap.addMarker(findAttraction("Corso Monforte, 2, 20122 Milano MI").title("Lego Store"));
                }else if(citta.equals("Roma RM, Italia")){
                     m1 = mMap.addMarker(findAttraction("Via dei Pastini, 96-98, 00186 Roma RM").title("Bartolucci Italy"));
                     m2 = mMap.addMarker(findAttraction("C.C. Forum, Stazione Termini, 00185 Roma RM").title("Foot Locker"));
                     m3 = mMap.addMarker(findAttraction("Piazza di Spagna, 77, 00187 Roma RM").title("Moncler"));
                }
                break;
            case 4: //RISTORANTI
                if (citta.equals("Milano MI, Italia")){
                     m1 = mMap.addMarker(findAttraction("Corso Vittorio Emanuele II, 20121 Milano MI").title("Ristorante Cracco"));
                     m2 = mMap.addMarker(findAttraction("Via Ugo Foscolo, 1, 20121 Milano MI").title("Gino Sorbillo"));
                     m3 = mMap.addMarker(findAttraction("Via Fiori Chiari, 1, 20121 Milano MI").title("Sushi-B"));
                }else if(citta.equals("Roma RM, Italia")){
                     m1 = mMap.addMarker(findAttraction("Via Florida, 25, 00186 Roma RM").title("Pizza Florida"));
                     m2 = mMap.addMarker(findAttraction("Via Francesco Crispi, 19, 00187 Roma RM").title("Ristorante Crispi 19"));
                     m3 = mMap.addMarker(findAttraction("Via del Gazometro, 54, 00154 Roma RM").title("Sakana Sushi"));
                }
                break;
            default:break;
        }

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                marker.showInfoWindow();
                mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick(Marker marker) {
                        String attrazione = marker.getTitle();

                        switch (categoria){
                            case 1: trip.getArte().add(attrazione);
                            break;
                            case 2: trip.getSport().add(attrazione);
                            break;
                            case 3: trip.getShopping().add(attrazione);
                            break;
                            case 4: trip.getRistoranti().add(attrazione);
                        }

                        marker.remove();
                    }
                });
                return false;
            }
        });
    }
}
