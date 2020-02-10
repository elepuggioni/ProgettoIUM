package com.example.progettoium;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class Activities extends AppCompatActivity {
    public static final String CATEGORIA = "Categoria";

    Button goBack, continua;
    TextView titleCity;
    ImageView arte, sport, shopping, risto;

    Trip viaggio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attrazioni);

        goBack = findViewById(R.id.goHome);
        continua = findViewById(R.id.continuaPlan2);
        titleCity = findViewById(R.id.titlePlan);
        arte = findViewById(R.id.arteImage);
        sport = findViewById(R.id.sportImage);
        shopping = findViewById(R.id.shoppingImage);
        risto = findViewById(R.id.ristoImage);

        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(Home.TRIP);

        if(obj instanceof Trip){
            viaggio = (Trip) obj;
        }else {
            viaggio = new Trip();
        }

        //scelta diverse categorie; viene passato un valore all'activity
        arte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showArte = new Intent(Activities.this, FindAttractions.class);
                showArte.putExtra(Home.TRIP, viaggio);
                showArte.putExtra(Activities.CATEGORIA, 1);
                startActivity(showArte);
            }
        });

        sport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showSport = new Intent(Activities.this, FindAttractions.class);
                showSport.putExtra(Home.TRIP, viaggio);
                showSport.putExtra(Activities.CATEGORIA, 2);
                startActivity(showSport);
            }
        });

        shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showShopping = new Intent(Activities.this, FindAttractions.class);
                showShopping.putExtra(Home.TRIP, viaggio);
                showShopping.putExtra(Activities.CATEGORIA, 3);
                startActivity(showShopping);
            }
        });

        risto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showRisto = new Intent(Activities.this, FindAttractions.class);
                showRisto.putExtra(Home.TRIP, viaggio);
                showRisto.putExtra(Activities.CATEGORIA, 4);
                startActivity(showRisto);
            }
        });


        //Continua su scelta amici
        /*continua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showContinua = new Intent(Activities.this, Friends.class);
                showContinua.putExtra(PlanTrip.VIAGGIO, viaggio);
                startActivity(showContinua);
            }
        });*/

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showHome = new Intent(Activities.this, PlanTrip.class);
                startActivity(showHome);
            }
        });
    }
}
