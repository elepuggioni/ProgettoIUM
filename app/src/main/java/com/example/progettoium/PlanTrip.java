package com.example.progettoium;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PlanTrip extends AppCompatActivity {
    Button goBack, continuaPlan1;
    TextView titleCity;
    ImageView image;
    Spinner alloggio;
    EditText partenza, ritorno;

    SeekBar budget;
    TextView bTitle;
    int minValue = 0;
    int maxValue = 1500;
    int modelValue = 0;

    Date data_partenza = null;
    Trip trip;
    DatePickerFragment datePickerFragment;
    Calendar scelta;
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plantrip);

        datePickerFragment = new DatePickerFragment();

        goBack = findViewById(R.id.goHome);
        titleCity = findViewById(R.id.titlePlan);
        image = findViewById(R.id.imgPlan);
        alloggio = findViewById(R.id.dropdownAlloggio);
        budget = findViewById(R.id.seekbarBudget);
        bTitle = findViewById(R.id.budgetTitle);
        continuaPlan1 = findViewById(R.id.continuaPlan1);
        partenza = findViewById(R.id.partenzaDate);
        ritorno = findViewById(R.id.ritornoDate);

        if(scelta==null){
            scelta = Calendar.getInstance();
            scelta.set(Calendar.YEAR, 1995);
            scelta.set(Calendar.MONTH, Calendar.JANUARY);
            scelta.set(Calendar.DAY_OF_MONTH, 1);
        }

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
            image.setImageResource(R.drawable.roma);
        }

        if (trip.getPartenza() != null)
            partenza.setText(format.format(trip.getPartenza().getTime()));

        if (trip.getRitorno() != null)
            ritorno.setText(format.format(trip.getRitorno().getTime()));



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

        if(trip.getBudget()!= 0){
            updateValue(trip.getBudget());
            budget.setProgress(trip.getBudget());
        }

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


/*   DATE   */

        partenza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (trip.getPartenza() != null){
                    int y = trip.getPartenza().get(Calendar.YEAR);
                    int m = trip.getPartenza().get(Calendar.MONTH);
                    int d = trip.getPartenza().get(Calendar.DAY_OF_MONTH);
                    // partenza
                    //datePickerFragment.initDatePicker(y,m,d);
                    Calendar sce = Calendar.getInstance();
                    sce.set(Calendar.YEAR, y);
                    sce.set(Calendar.MONTH, m);
                    sce.set(Calendar.DAY_OF_MONTH,d);
                    datePickerFragment.setDate(sce);

                }

                datePickerFragment.setCaso(0);
                datePickerFragment.show(getSupportFragmentManager(),"date picker");
            }
        });

        partenza.setOnFocusChangeListener(new View.OnFocusChangeListener() { //funzione di view
            @Override
            public void onFocusChange(View v, boolean hasFocus) { //metodo chiamato quando lo stato della view cambia
                if(hasFocus){
                    datePickerFragment.setCaso(0);
                    datePickerFragment.show(getSupportFragmentManager(), "datePicker");
                }
            }
        });

        ritorno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (trip.getRitorno() != null)  // partenza
                    datePickerFragment.setDate(trip.getRitorno());

                datePickerFragment.setCaso(1);
                datePickerFragment.show(getSupportFragmentManager(),"date picker");
            }
        });

        ritorno.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                datePickerFragment.setCaso(1);
                datePickerFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

        datePickerFragment.setOnDatePickerFragmentChanged(new DatePickerFragment.DatePickerFragmentListener() {
            @Override
            public void onDatePickerFragmentOkButton(DialogFragment dialog, Calendar date) {

/* ATTENZIONE CI SONO INTERFERENZE CON LE DATE */
                if(datePickerFragment.getCaso() == 0){
                    data_partenza = date.getTime();
                    partenza.setText(format.format(date.getTime()));
                    trip.setPartenza(date);
                }else{
                    ritorno.setText(format.format(date.getTime()));
                    trip.setRitorno(date);
                }
            }

            @Override
            public void onDatePickerFragmentCancelButton(DialogFragment dialog) {
            }
        });

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

                trip.setBudget(modelValue);
                Intent showFindAttr = new Intent(PlanTrip.this, Activities.class);
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
