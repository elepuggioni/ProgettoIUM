package com.example.progettoium;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
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
    EditText mSearch;
    Trip trip;
    int categoria;
    Button confirm, removeTop3;
    CheckBox cb1, cb2, cb3;
    LinearLayout top3, checkboxes;
    Marker m1,m2,m3;

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
        mSearch = findViewById(R.id.attraction_search);

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

        confirm = findViewById(R.id.attraction_confirm);
        /*confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });*/

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

    private void init(){
        mSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent keyEvent) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH
                        || actionId== EditorInfo.IME_ACTION_DONE
                        || keyEvent.getAction() == KeyEvent.ACTION_DOWN
                        || keyEvent.getAction() == KeyEvent.KEYCODE_ENTER){
                    moveToAttraction();
                }
                return false;
            }
        });
    }

    public void moveToAttraction(){
        Marker address = mMap.addMarker(findAttraction(mSearch.getText().toString()).visible(false));
        //Si muove solo se l'utente ha cercato un'attrazione nella top 3

        if (address.getPosition()==m1.getPosition()){
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m1.getPosition(),13F));
        }
        if (address.getPosition()==m2.getPosition()){
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m2.getPosition(),13F));
        }
        if (address.getPosition()==m3.getPosition()){
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m3.getPosition(),13F));
        }
    }

    public void showAttractions(final int categoria, String citta){
        Marker m4 = mMap.addMarker(new MarkerOptions().position(new LatLng(0,0)).visible(false));
        Marker m5 = mMap.addMarker(new MarkerOptions().position(new LatLng(0,0)).visible(false));;

        switch (categoria){
            case 1: //ARTE
                if (citta.equals("Milano MI, Italia")){
                     m1 = mMap.addMarker(findAttraction("Duomo di Milano").title("Il Duomo"));
                     m2 = mMap.addMarker(findAttraction("Castello Sforzesco").title("Castello Sforzesco"));
                     m3 = mMap.addMarker(findAttraction("Teatro alla Scala").title("Teatro alla Scala"));
                     m4 = mMap.addMarker(findAttraction("Pinacoteca di Brera").title("Pinacoteca di Brera"));
                     m5 = mMap.addMarker(findAttraction("Galleria d'arte Milano").title("Galleria d'arte"));

                }else if(citta.equals("Roma RM, Italia")){
                     m1 = mMap.addMarker(findAttraction("Colosseo").title("Il Colosseo"));
                     m2 = mMap.addMarker(findAttraction("Patheon Roma").title("Il Pantheon"));
                     m3 = mMap.addMarker(findAttraction("Fontana di Trevi").title("Fontana di Trevi"));
                     m4 = mMap.addMarker(findAttraction("Musei capitolini").title("Musei Capitolini"));
                     m5 = mMap.addMarker(findAttraction("Ara Pacis Roma").title("Ara Pacis"));

                }

                break;
            case 2: //SPORT
                if (citta.equals("Milano MI, Italia")){
                     m1 = mMap.addMarker(findAttraction("Stadio Meazza").title("Stadio Meazza"));
                     m2 = mMap.addMarker(findAttraction("Inter Store Milano").title("Inter Store"));
                     m3 = mMap.addMarker(findAttraction("Milan Store Milano").title("Milan Store"));
                     m4 = mMap.addMarker(findAttraction("Via Cesare Beccaria, 2, 20122 Milano MI").title("Cisalfa Sport"));
                     m5 = mMap.addMarker(findAttraction("Via Mauro Macchi, 13, 20124 Milano MI").title("La Montagna Sport"));

                }else if(citta.equals("Roma RM, Italia")){
                     m1 = mMap.addMarker(findAttraction("Stadio Olimpico Roma").title("Stadio Olimpico"));
                     m2 = mMap.addMarker(findAttraction("AS Roma Store Roma").title("Roma Store"));
                     m3 = mMap.addMarker(findAttraction("Sport Center store roma").title("Sport Center Store"));
                     m4 = mMap.addMarker(findAttraction(" Via di Campo Marzio, 38, 00186 Roma RM").title("Banchetti Sport"));
                     m5 = mMap.addMarker(findAttraction("Via della Traspontina, 19, 00193 Roma RM").title("Ogni Sport"));

                }
                break;
            case 3: //SHOPPING
                if (citta.equals("Milano MI, Italia")){
                     m1 = mMap.addMarker(findAttraction("Galleria Vittorio Emanuele Milano").title("Galleria Vittorio Emanuele"));
                     m2 = mMap.addMarker(findAttraction("The Merchant Of Venice - Milano Boutique").title("The Merchant Of Venice"));
                     m3 = mMap.addMarker(findAttraction("Corso Monforte, 2, 20122 Milano MI").title("Lego Store"));
                     m4 = mMap.addMarker(findAttraction("Corso Vittorio Emanuele II, 30, 20121 Milano MI").title("The Higline Outlet"));
                     m5 = mMap.addMarker(findAttraction("Via Santa Radegonda, 3, 20121 Milano MI").title("La Rinascente"));
                }else if(citta.equals("Roma RM, Italia")){
                     m1 = mMap.addMarker(findAttraction("Via dei Pastini, 96-98, 00186 Roma RM").title("Bartolucci Italy"));
                     m2 = mMap.addMarker(findAttraction("C.C. Forum, Stazione Termini, 00185 Roma RM").title("Foot Locker"));
                     m3 = mMap.addMarker(findAttraction("Piazza di Spagna, 77, 00187 Roma RM").title("Moncler"));
                     m4 = mMap.addMarker(findAttraction("Piazza Colonna, 00187 Roma RM").title("Galleria Alberto Sordi"));
                     m5 = mMap.addMarker(findAttraction("Via del Tritone, 61, 00187 Roma RM").title("La Rinascente"));
                }
                break;
            case 4: //RISTORANTI
                if (citta.equals("Milano MI, Italia")){
                     m1 = mMap.addMarker(findAttraction("Carlo cracco Milano").title("Ristorante Cracco"));
                     m2 = mMap.addMarker(findAttraction("Gino sorbillo Milano").title("Gino Sorbillo"));
                     m3 = mMap.addMarker(findAttraction("Sushi-B milano").title("Sushi-B"));
                     m4 = mMap.addMarker(findAttraction("L'immagine bistrot ristorante Milano").title("L'Immagine Bistrot"));
                     m5 = mMap.addMarker(findAttraction("Eataly milano smeraldo").title("Eataly"));
                }else if(citta.equals("Roma RM, Italia")){
                     m1 = mMap.addMarker(findAttraction("Via Florida, 25, 00186 Roma RM").title("Pizza Florida"));
                     m2 = mMap.addMarker(findAttraction("Via Francesco Crispi, 19, 00187 Roma RM").title("Ristorante Crispi 19"));
                     m3 = mMap.addMarker(findAttraction("Via del Gazometro, 54, 00154 Roma RM").title("Sakana Sushi"));
                     m4 = mMap.addMarker(findAttraction("Via Santa Maria in Via, 19, 00187 Roma RM").title("Pane E Salame"));
                     m5 = mMap.addMarker(findAttraction("Via della Paglia, 1, 00153 Roma RM").title("Tonnarello"));
                }
                break;
            default:break;
        }

        cb1.setText(m1.getTitle());
        cb2.setText(m2.getTitle());
        cb3.setText(m3.getTitle());

        //Nessuno dei marker è stato selezionato
        m1.setTag((Integer) 0);
        m2.setTag((Integer) 0);
        m3.setTag((Integer)0);
        m4.setTag((Integer)0);
        m5.setTag((Integer)0);


        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                marker.showInfoWindow();
                mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick(Marker marker) {
                        changeMarker(categoria,marker);
                    }
                });
                return false;
            }
        });

        //Prepara la schermata
        init();

    }

    public void changeCheckbox(int caso, String attrazione, CheckBox cb){
        switch (caso){
            case 0: //E' stato selezionato il marker
                if (cb.getText().equals(attrazione)){
                    cb.setChecked(true);
                }
                break;
            case 1: //E' stato eliminato il marker
                if (cb.getText().equals(attrazione)){
                    cb.setChecked(false);
                }
                break;
        }
    }

    public void changeMarker (int categoria, Marker marker){
        String attrazione = marker.getTitle();

        if(marker.getTag() == (Integer)0){   //Non era selezionato;
            // Aggiungo alla lista di attrazioni corrispondente, l'attrazione associata al marker

            switch (categoria){
                case 1: trip.getArte().add(attrazione);
                    break;
                case 2: trip.getSport().add(attrazione);
                    break;
                case 3: trip.getShopping().add(attrazione);
                    break;
                case 4: trip.getRistoranti().add(attrazione);
            }
            //Il marker diventa verde
            marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
            marker.setTag((Integer)1);   //Ora può essere rimosso

            //Se è stata selezionata un'attrazione tra le top 3, si checka la casella corrispondente
            changeCheckbox(0,attrazione,cb1);
            changeCheckbox(0,attrazione,cb2);
            changeCheckbox(0,attrazione,cb3);
        }else{
            // Tolgo dalla lista di attrazioni corrispondente, l'attrazione associata al marker

            switch (categoria){
                case 1: trip.getArte().remove(attrazione);
                    break;
                case 2: trip.getSport().remove(attrazione);
                    break;
                case 3: trip.getShopping().remove(attrazione);
                    break;
                case 4: trip.getRistoranti().remove(attrazione);
            }
            changeCheckbox(1,attrazione,cb1);
            changeCheckbox(1,attrazione,cb2);
            changeCheckbox(1,attrazione,cb3);
            //Il marker diventa rosso
            marker.setIcon(BitmapDescriptorFactory.defaultMarker());
            marker.setTag((Integer)0);   //Ora può essere riselezionato
        }
        marker.hideInfoWindow();
    }

    public void onCheckboxClicked(View view) {

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.cb1:
                changeMarker(categoria,m1);
                break;
            case R.id.cb2:
                changeMarker(categoria,m2);
                break;
            case R.id.cb3:
                changeMarker(categoria,m3);
                break;
        }
    }

}
