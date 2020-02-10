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
    Button goBack, continua;
    TextView titleCity;
    ImageView arte, sport, shopping, risto;

    Trip viaggio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities);

        goBack = findViewById(R.id.goHome);
        continua = findViewById(R.id.continuaPlan2);
        titleCity = findViewById(R.id.titlePlan);
        arte = findViewById(R.id.arteImage);
        sport = findViewById(R.id.sportImage);
        shopping = findViewById(R.id.shoppingImage);
        risto = findViewById(R.id.ristoImage);

        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(PlanTrip.VIAGGIO);

        if(obj instanceof Trip){
            viaggio = (Trip) obj;
        }else {
            viaggio = new Trip();
        }

        //scelta diverse categorie
        /*arte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showArte = new Intent(Activities.this, ChooseArt.class);
                showArte.putExtra(PlanTrip.VIAGGIO, viaggio);
                startActivity(showArte);
            }
        });

        sport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showSport = new Intent(Activities.this, ChooseSport.class);
                showSport.putExtra(PlanTrip.VIAGGIO, viaggio);
                startActivity(showSport);
            }
        });

        shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showShopping = new Intent(Activities.this, ChooseShopping.class);
                showShopping.putExtra(PlanTrip.VIAGGIO, viaggio);
                startActivity(showShopping);
            }
        });

        risto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showRisto = new Intent(Activities.this, ChooseRestaurant.class);
                showRisto.putExtra(PlanTrip.VIAGGIO, viaggio);
                startActivity(showRisto);
            }
        });*/


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
