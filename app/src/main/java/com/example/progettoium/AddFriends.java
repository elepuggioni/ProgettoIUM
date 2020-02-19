package com.example.progettoium;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class AddFriends extends AppCompatActivity {
    Button goBack, continua;
    CheckBox amico1, amico2;
    TextView nomeAmico1, nomeAmico2;

    Trip viaggio;
    Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friends);

        goBack = findViewById(R.id.goHome);
        continua = findViewById(R.id.continuaPlan3);
        amico1 = findViewById(R.id.scegliAmico1);
        amico2 = findViewById(R.id.scegliAmico2);
        nomeAmico1 = findViewById(R.id.nomeAmico1);
        nomeAmico2 = findViewById(R.id.nomeAmico2);

        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(Home.TRIP);

        if(obj instanceof Trip){
            viaggio = (Trip) obj;
        }else {
            viaggio = new Trip();
        }

        Serializable obj2 = intent.getSerializableExtra(Register.PERSONA);

        if(obj2 instanceof Person){
            person = (Person) obj2;
        }else {
            person = new Person();
        }

        for (String s: viaggio.getAmici()){
            if (s.equals("@maryjanewatson")){
                amico1.setChecked(true);
            }
            if (s.equals("@markruffalo")){
                amico2.setChecked(true);
            }
        }

        amico1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amico1.isChecked() && !(viaggio.getAmici().contains(nomeAmico1.getText().toString()))) {
                    viaggio.getAmici().add(nomeAmico1.getText().toString());
                }
                else if(!(amico1.isChecked())){
                    viaggio.getAmici().remove(nomeAmico1.getText().toString());

                }
            }
        });

        amico2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (amico2.isChecked() && !(viaggio.getAmici().contains(nomeAmico2.getText().toString()))) {
                    viaggio.getAmici().add(nomeAmico2.getText().toString());
                }
                else if(!(amico2.isChecked())){
                    viaggio.getAmici().remove(nomeAmico2.getText().toString());
                }
            }
        });

        //Continua su conferma
        continua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showContinua = new Intent(AddFriends.this, ConfermaTotale.class);
                showContinua.putExtra(Home.TRIP, viaggio);
                showContinua.putExtra(Register.PERSONA,person);
                startActivity(showContinua);
            }
        });


        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showHome = new Intent(AddFriends.this, Activities.class);
                showHome.putExtra(Home.TRIP, viaggio);
                showHome.putExtra(Register.PERSONA,person);
                startActivity(showHome);
            }
        });
    }
}
