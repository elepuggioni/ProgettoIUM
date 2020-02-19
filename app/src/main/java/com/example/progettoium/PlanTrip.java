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
import java.text.ParseException;
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
    Person person;
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

        if (scelta == null) {
            scelta = Calendar.getInstance();
            scelta.set(Calendar.YEAR, 1995);
            scelta.set(Calendar.MONTH, Calendar.JANUARY);
            scelta.set(Calendar.DAY_OF_MONTH, 1);
        }

        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(Home.TRIP);

        if (obj instanceof Trip) {
            trip = (Trip) obj;
        } else {
            trip = new Trip();
        }

        Serializable obj2 = intent.getSerializableExtra(Register.PERSONA);

        if(obj2 instanceof Person){
            person = (Person) obj2;
        }else {
            person = new Person();
        }

        //Cambio le scritte in base alla città scelta dall'utente
        titleCity.setText(trip.getCity());
        if (trip.getCity().equals("Milano MI, Italia")) {
            image.setImageResource(R.drawable.milano);
        }

        if (trip.getCity().equals("Roma RM, Italia")) {
            image.setImageResource(R.drawable.roma);
        }

        if (trip.getPartenza() != null)
            partenza.setText(format.format(trip.getPartenza().getTime()));

        if (trip.getRitorno() != null)
            ritorno.setText(format.format(trip.getRitorno().getTime()));


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                (this, R.array.tipo_alloggio, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        alloggio.setAdapter(adapter);
        alloggio.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = parent.getItemAtPosition(position).toString();
                trip.setAlloggio(selection);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        if (trip.getBudget() != 0) {
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
            //All'inizio la data iniziale è quella odierna
            int[] i = {getToday().get(Calendar.YEAR),getToday().get(Calendar.MONTH), getToday().get(Calendar.DAY_OF_MONTH)};

            @Override
            public void onClick(View v) {
                if (trip.getPartenza() != null) {
                    int y = trip.getPartenza().get(Calendar.YEAR);
                    int m = trip.getPartenza().get(Calendar.MONTH);
                    int d = trip.getPartenza().get(Calendar.DAY_OF_MONTH);
                    //Se è già settata la partenza, si cambia la data iniziale
                    i[0] = y;
                    i[1]= m;
                    i[2] = d;

                }
                if(trip.getRitorno() != null){  //Se è settata la data di ritorno, si setta la data massima
                    datePickerFragment.setMax_date(trip.getRitorno());
                }

                datePickerFragment.resetMin();  //Resetto la data minima
                datePickerFragment.setInizio(i);
                datePickerFragment.setCaso(0);
                datePickerFragment.show(getSupportFragmentManager(), "date picker");
            }
        });

        partenza.setOnFocusChangeListener(new View.OnFocusChangeListener() { //funzione di view
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    datePickerFragment.setCaso(0);
                    datePickerFragment.show(getSupportFragmentManager(), "datePicker");
                }
            }
        });

        ritorno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //All'inizio viene settata la data odierna
                int[] i = {getToday().get(Calendar.YEAR),getToday().get(Calendar.MONTH), getToday().get(Calendar.DAY_OF_MONTH)};

                if (trip.getRitorno() != null) {
                    //Se era stata già settata la data di ritorno, si mostra quella
                    int y = trip.getRitorno().get(Calendar.YEAR);
                    int m = trip.getRitorno().get(Calendar.MONTH);
                    int d = trip.getRitorno().get(Calendar.DAY_OF_MONTH);

                    i[0] = y;
                    i[1]= m;
                    i[2] = d;
                }
                if (trip.getPartenza() != null) {
                    //Se era stata settata una data di partenza, la si setta come data minima
                    datePickerFragment.setMin_date(trip.getPartenza());
                }

                datePickerFragment.setInizio(i);
                datePickerFragment.resetMax();  //Resetto la data massima
                datePickerFragment.setCaso(1);
                datePickerFragment.show(getSupportFragmentManager(), "date picker");
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
                setDataScelta(date);
            }

            @Override
            public void onDatePickerFragmentCancelButton(DialogFragment dialog) {
            }
        });

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showHome = new Intent(PlanTrip.this, Home.class);
                showHome.putExtra(Register.PERSONA, person);
                startActivity(showHome);
            }
        });

        continuaPlan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                trip.setBudget(modelValue);
                Intent showFindAttr = new Intent(PlanTrip.this, Activities.class);
                showFindAttr.putExtra(Home.TRIP, trip);
                showFindAttr.putExtra(Register.PERSONA, person);
                startActivity(showFindAttr);
            }
        });
    }

    protected void updateValue(int newValue) {
        //verifico che newValue sia nel range e minore di 1500
        newValue = newValue > maxValue ? maxValue : newValue;
        //verifico che newValue sia nel range e maggiore di 0
        newValue = newValue < minValue ? minValue : newValue;

        //aggiorno il valore visualizzato sulla seekbar
        if (this.budget.getProgress() != modelValue) {
            this.budget.setProgress(modelValue);
        }

        this.modelValue = newValue;
        bTitle.setText("Budget: " + this.modelValue);
    }

    public void setDataScelta(Calendar data) {

        Calendar d = Calendar.getInstance();

        if (datePickerFragment.getCaso() == 0) {
            data_partenza = data.getTime();
            partenza.setText(format.format(data.getTime()));
            Date da = new Date();

            try {
                da = format.parse(partenza.getText().toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            d.setTime(da);
            trip.setPartenza(d);
        } else {
            ritorno.setText(format.format(data.getTime()));
            Date da = new Date();

            try {
                da = format.parse(ritorno.getText().toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            d.setTime(da);
            trip.setRitorno(d);
        }
    }

    public Calendar getToday() {
        Calendar calendar = Calendar.getInstance();
        Calendar cal = Calendar.getInstance();
        int yy = calendar.get(Calendar.YEAR);
        int mm = calendar.get(Calendar.MONTH);
        int dd = calendar.get(Calendar.DAY_OF_MONTH);

        cal.set(Calendar.MONTH, mm);
        cal.set(Calendar.DAY_OF_MONTH, dd);
        cal.set(Calendar.YEAR, yy);
        return cal;
    }

}
