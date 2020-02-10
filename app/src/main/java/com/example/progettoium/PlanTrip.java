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

import java.io.Serializable;

public class PlanTrip extends AppCompatActivity {
    Button goBack, continuaPlan1;
    TextView titleCity;
    ImageView image;
    Spinner alloggio;

    SeekBar budget;
    TextView bTitle;
    int minValue = 0;
    int maxValue = 1500;
    int modelValue = 0;

    Trip trip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plantrip);

        goBack = findViewById(R.id.goHome);
        titleCity = findViewById(R.id.titlePlan);
        image = findViewById(R.id.imgPlan);
        alloggio = findViewById(R.id.dropdownAlloggio);
        budget = findViewById(R.id.seekbarBudget);
        bTitle = findViewById(R.id.budgetTitle);
        continuaPlan1 = findViewById(R.id.continuaPlan1);

        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(Home.TRIP);

        if(obj instanceof Trip){
            trip = (Trip) obj;
        }else {
            trip = new Trip();
        }

        //Cambio le scritte in base alla città scelta dall'utente
        titleCity.setText(trip.getCity());
        if(trip.getCity().equals("Milano MI, Italia")){
            image.setImageResource(R.drawable.milano);
        }

        if (trip.getCity().equals("Roma RM, Italia")){
            image.setImageResource(R.drawable.romap);
        }

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                (this,R.array.tipo_alloggio, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        alloggio.setAdapter(adapter);
        alloggio.setOnItemSelectedListener(new OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = parent.getItemAtPosition(position).toString();
                trip.setAlloggio(selection);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });


        budget.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateValue(seekBar.getProgress());
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                updateValue(seekBar.getProgress());
            }
        });

        trip.setBudget(modelValue);

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showHome = new Intent(PlanTrip.this, Home.class);
                startActivity(showHome);
            }
        });

        continuaPlan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showFindAttr = new Intent(PlanTrip.this, FindAttractions.class);
                showFindAttr.putExtra(Home.TRIP, trip);
                startActivity(showFindAttr);
            }
        });
    }

    protected void updateValue(int newValue){
        //verifico che newValue sia nel range e minore di 100
        newValue = newValue > maxValue ? maxValue : newValue;
        //verifico che newValue sia nel range e maggiore di 0
        newValue = newValue < minValue ? minValue : newValue;

        //aggiorno il valore visualizzato sulla seekbar
        if(this.budget.getProgress() != modelValue){
            this.budget.setProgress(modelValue);
        }

        //aggiorno la variabile che indica il valore attuale della calcolatrice
        this.modelValue = newValue;
        bTitle.setText("Budget: "+this.modelValue);
    }
}
