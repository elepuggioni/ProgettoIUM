package com.example.progettoium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

public class PlanTrip extends AppCompatActivity {
    public static final String VIAGGIO = "Viaggio";

    Button goBack;
    TextView titleCity;
    ImageView image;
    Spinner alloggio;
    SeekBar budget;

    Trip viaggio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plantrip);

        goBack = findViewById(R.id.goHome);
        titleCity = findViewById(R.id.titlePlan);
        image = findViewById(R.id.imgPlan);
        alloggio = findViewById(R.id.dropdownAlloggio);
        budget = findViewById(R.id.seekbarBudget);

        viaggio = new Trip();
        viaggio.setCity(titleCity.toString());

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                (this,R.array.tipo_alloggio, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        alloggio.setAdapter(adapter);



        alloggio.setOnItemSelectedListener(new OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = parent.getItemAtPosition(position).toString();
                viaggio.setAlloggio(selection);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });



        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showHome = new Intent(PlanTrip.this, Home.class);
                startActivity(showHome);
            }
        });

    }
}
