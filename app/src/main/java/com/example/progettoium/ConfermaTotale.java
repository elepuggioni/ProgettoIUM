package com.example.progettoium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;
import java.util.Calendar;

public class ConfermaTotale extends AppCompatActivity {
    Button goBack, conferma, annulla;
    Trip viaggio;
    TextView partenza_citta, partenza_data, ritorno_citta, ritorno_data;
    TextView arte, shopping, ristoranti, sport, budget, alloggio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conferma_totale);

        goBack = findViewById(R.id.go_back);
        conferma = findViewById(R.id.conferma);
        annulla = findViewById(R.id.annulla);

        partenza_citta = findViewById(R.id.partenza_citta);
        partenza_data = findViewById(R.id.partenza_data_text);
        ritorno_citta = findViewById(R.id.ritorno_citta);
        ritorno_data = findViewById(R.id.ritorno_data_text);
        arte = findViewById(R.id.conferma_arte);
        shopping = findViewById(R.id.conferma_shopping);
        ristoranti = findViewById(R.id.conferma_ristoranti);
        sport = findViewById(R.id.conferma_sport);
        budget = findViewById(R.id.budget);
        alloggio = findViewById(R.id.tipo_alloggio);


        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(Home.TRIP);

        if(obj instanceof Trip){
            viaggio = (Trip) obj;
        }else {
            viaggio = new Trip();
        }

        //partenza_citta.setText();
        if(viaggio.getPartenza() != null){
            int m = viaggio.getPartenza().get(Calendar.MONTH) +1;
            partenza_data.setText(viaggio.getPartenza().get(Calendar.DAY_OF_MONTH) + "/"+
                    m+"/"+viaggio.getPartenza().get(Calendar.YEAR));
        }
        ritorno_citta.setText(viaggio.getCity());
        if(viaggio.getRitorno() != null){
            int m = viaggio.getRitorno().get(Calendar.MONTH) +1;
            ritorno_data.setText(viaggio.getRitorno().get(Calendar.DAY_OF_MONTH) + "/"+
                    m+"/"+viaggio.getRitorno().get(Calendar.YEAR));
        }

        arte.setText(": "+viaggio.getArte().size()+" monumenti");
        shopping.setText(": "+viaggio.getShopping().size()+" negozi");
        ristoranti.setText(": "+viaggio.getRistoranti().size()+" ristoranti");
        sport.setText(": "+viaggio.getSport().size()+" attrazioni");
        budget.setText(viaggio.getBudget()+" euro");
        alloggio.setText(": "+viaggio.getAlloggio());

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
