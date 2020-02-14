package com.example.progettoium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;

public class ConfermaTotale extends AppCompatActivity {
    Button goBack, conferma, annulla;
    Trip viaggio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conferma_totale);

        goBack = findViewById(R.id.go_back);
        conferma = findViewById(R.id.conferma);
        annulla = findViewById(R.id.annulla);

        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(Home.TRIP);

        if(obj instanceof Trip){
            viaggio = (Trip) obj;
        }else {
            viaggio = new Trip();
        }


        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showAddFriends = new Intent(ConfermaTotale.this, AddFriends.class);
                showAddFriends.putExtra(Home.TRIP, viaggio);
                startActivity(showAddFriends);
            }
        });

        annulla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showAddFriends = new Intent(ConfermaTotale.this, AddFriends.class);
                showAddFriends.putExtra(Home.TRIP, viaggio);
                startActivity(showAddFriends);
            }
        });

        conferma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do stuff
            }
        });
    }
}
