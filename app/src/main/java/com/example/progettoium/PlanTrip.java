package com.example.progettoium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PlanTrip extends AppCompatActivity{
    Button goBack;
    TextView titleCity;
    ImageView image;
    //City city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plantrip);

        goBack = findViewById(R.id.goHome);
        titleCity = findViewById(R.id.titlePlan);
        image = findViewById(R.id.imgPlan);


        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showHome = new Intent(PlanTrip.this, Home.class);
                startActivity(showHome);
            }
        });
    }
}
