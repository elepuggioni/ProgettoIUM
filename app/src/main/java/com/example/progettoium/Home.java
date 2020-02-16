package com.example.progettoium;

import androidx.annotation.DrawableRes;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Home extends FragmentActivity implements OnMapReadyCallback {
    public static final String TRIP = "Trip";
    private GoogleMap mMap;
    ImageButton profilo;
    Button random;
    EditText mSearchText;
    Person person;
    Trip trip;

    //Widgets
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
        mSearchText = findViewById(R.id.search);
        random = findViewById(R.id.random);

        trip = new Trip();  //E' stato creato un nuovo viaggio
        trip.setDeparture_city(person.getCitta());  //La città di partenza è quella della persona

        profilo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showProfile = new Intent(Home.this, Profile.class);
                showProfile.putExtra(Register.PERSONA,person);
                startActivity(showProfile);
            }
        });

        init();


        random.setOnClickListener(new View.OnClickListener() {
            int i = 0;
            @Override
            public void onClick(View v) {

                if (i==0){
                    geoLocate("Milano");
                    i = 1;
                }else {
                    geoLocate("Roma");
                    i=0;
                }
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

    private void init(){
        mSearchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent keyEvent) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH
                || actionId== EditorInfo.IME_ACTION_DONE
                || keyEvent.getAction() == KeyEvent.ACTION_DOWN
                || keyEvent.getAction() == KeyEvent.KEYCODE_ENTER){
                    geoLocate(mSearchText.getText().toString());
                }
                return false;
            }
        });
    }

    private void geoLocate(String search){
        String searchString = search;

        Geocoder geocoder = new Geocoder(Home.this);
        List<Address> list = new ArrayList<>();
        try {
            list = geocoder.getFromLocationName(searchString,1);
        }catch (IOException e){

        };

        if(list.size()>0){
            Address address = list.get(0);
            moveCamera(address);
        }

    }

    private void moveCamera(final Address address){
        mMap.clear();
        LatLng city = new LatLng(address.getLatitude(), address.getLongitude());
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(city, 5.5F));

        mMap.setInfoWindowAdapter(new CityWindow(Home.this));

        final Marker marker = mMap.addMarker(new MarkerOptions()
                .position(city)
                .title(address.getAddressLine(0))
        );

        int height = 1;
        int width = 1;
        BitmapDrawable bitmapdraw = (BitmapDrawable)getResources().getDrawable(R.drawable.heart);
        Bitmap b = bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);

        marker.setIcon(BitmapDescriptorFactory.fromBitmap(smallMarker));
        marker.showInfoWindow();

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent showPlanTrip = new Intent(Home.this, PlanTrip.class);
                trip.setCity(address.getAddressLine(0));
                showPlanTrip.putExtra(Home.TRIP, trip );
                showPlanTrip.putExtra(Register.PERSONA, person);
                startActivity(showPlanTrip);
            }
        });
    }

}
