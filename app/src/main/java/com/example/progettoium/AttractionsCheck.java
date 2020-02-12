package com.example.progettoium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AttractionsCheck extends AppCompatActivity {

    TextView title, subtitle1;
    Trip trip;
    Integer categoria;
    LinearLayout checkboxes;
    List<String> lista = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attractions_check);

        title = findViewById(R.id.check_title);
        subtitle1 =findViewById(R.id.check_subtitle1);

        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(Home.TRIP);

        if(obj instanceof Trip){
            trip = (Trip) obj;
        }else {
            trip = new Trip();
        }

        title.setText(trip.getCity());

        int obj2 = intent.getIntExtra(Activities.CATEGORIA,0);
        if (obj2!= 0){
            categoria = obj2;
        }

        switch (categoria){
            case 1: subtitle1.setText("ARTE");
                lista = trip.getArte();
                break;
            case 2: subtitle1.setText("SPORT");
                lista = trip.getSport();
                break;
            case 3: subtitle1.setText("SHOPPING");
                lista = trip.getShopping();
                break;
            case 4: subtitle1.setText("RISTORANTI");
                lista = trip.getRistoranti();
                break;
            default: subtitle1.setText("CATEGORIA NON TROVATA");
        }

        checkboxes = findViewById(R.id.check_checkboxes);
        createCheck();
    }
    public void onCheckboxClicked(View view) {
        CheckBox c = (CheckBox) view;

        if (!c.isChecked()){
            lista.remove(c.getId());
        } else
            lista.add(c.getText().toString());
    }

    public void createCheck(){
        CheckBox c = new CheckBox(getApplicationContext());
        int i;
        for (i=0; i<lista.size(); i++){
            c.setHeight(50);
            c.setPadding(20,10,0,0);
            c.setClickable(true);
            c.setText(lista.get(i).toString());
            c.setId(i);
            checkboxes.addView(c);
        }
    }

    public void removeObj(){

    }

}
