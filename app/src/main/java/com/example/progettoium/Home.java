package com.example.progettoium;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.Serializable;

public class Home extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    ImageButton profilo;
    Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(Register.PERSONA);

        if(obj instanceof Person){
            person = (Person) obj;
        }else {
            person = new Person();
        }

        profilo = findViewById(R.id.profile);

        profilo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showProfile = new Intent(Home.this, Profile.class);
                showProfile.putExtra(Register.PERSONA,person);
                startActivity(showProfile);
            }
        });
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng cagliari = new LatLng(39.226979, 9.114642);
        mMap.addMarker(new MarkerOptions().position(cagliari).title("Marker in Cagliari"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cagliari, 5.5F));
    }
}
