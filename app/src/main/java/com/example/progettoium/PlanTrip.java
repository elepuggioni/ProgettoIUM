package com.example.progettoium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
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

    Button goBack, continua;
    TextView titleCity;
    ImageView image;
    Spinner alloggio;

    SeekBar budget;
    TextView bTitle;
    int minValue = 0;
    int maxValue = 1500;
    int modelValue = 0;

    Trip viaggio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plantrip);

        goBack = findViewById(R.id.goHome);
        continua = findViewById(R.id.continuaPlan1);
        titleCity = findViewById(R.id.titlePlan);
        image = findViewById(R.id.imgPlan);
        alloggio = findViewById(R.id.dropdownAlloggio);
        budget = findViewById(R.id.seekbarBudget);
        bTitle = findViewById(R.id.budgetTitle);

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


        budget.incrementProgressBy(50);
        budget.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress = progress / 50;
                progress = progress * 50;
                bTitle.setText("Budget: "+ progress);
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

        viaggio.setBudget(modelValue);

        continua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showContinua = new Intent(PlanTrip.this, Activities.class);
                showContinua.putExtra(VIAGGIO, viaggio);
                startActivity(showContinua);
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
    }
}
