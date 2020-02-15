package com.example.progettoium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AttractionsCheck extends AppCompatActivity {
    public final static String FLAG = "Flag";
    TextView title, subtitle1;
    Trip trip;
    Integer categoria;
    LinearLayout checkboxes;
    List<String> lista = new ArrayList<>();
    Button confirm;

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
        if (obj2 != 0){
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
        confirm = findViewById(R.id.check_confirm);
        createCheck();

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (categoria){
                    case 1:
                        trip.setArte(lista);
                        break;
                    case 2:
                        trip.setSport(lista);
                        break;
                    case 3:
                        trip.setShopping(lista);
                        break;
                    case 4:
                        trip.setRistoranti(lista);
                        break;
                    default: subtitle1.setText("CATEGORIA NON TROVATA");
                }


                Intent showAttr = new Intent(AttractionsCheck.this, Activities.class);
                showAttr.putExtra(Home.TRIP, trip);
                showAttr.putExtra(AttractionsCheck.FLAG,true);
                startActivity(showAttr);
            }
        });

    }
    public void onCheckboxClicked(View view) {
        CheckBox c = (CheckBox) view;

        if (!c.isChecked()){
            lista.remove(c.getId());
        } else
            lista.add(c.getText().toString());
    }

    public void createCheck(){
        int i;

        for (i = 0; i < lista.size(); i++){
            CheckBox c = new CheckBox(this);
            c.setHeight(125);
            c.setClickable(true);
            c.setChecked(true);
            c.setText(lista.get(i).toString());
            c.setId(i);
            c.setPadding(16, 8, 16, 0);
            c.setTextSize(16);
            c.setTextColor(Color.parseColor("#808080"));
            checkboxes.addView(c);
        }
    }

    public void removeObj(){

    }

}
