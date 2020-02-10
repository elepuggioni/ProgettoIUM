package com.example.progettoium;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FindAttractions extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    TextView title;
    Trip trip;
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
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(citta, 12F));
        }
    }
}
