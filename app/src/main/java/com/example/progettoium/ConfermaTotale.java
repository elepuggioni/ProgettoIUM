package com.example.progettoium;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.Calendar;

public class ConfermaTotale extends AppCompatActivity {
    Button goBack, conferma, annulla, delete_viaggio, undo;
    Trip viaggio;
    ImageView amico1, amico2;
    TextView partenza_citta, partenza_data, ritorno_citta, ritorno_data, delete_subtitle;
    TextView arte, shopping, ristoranti, sport, budget, alloggio;

    Person person;
    Dialog dialog_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conferma_totale);
        dialog_delete= new Dialog(this);

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
        amico1 = findViewById(R.id.foto1);
        amico2 = findViewById(R.id.foto2);

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

        partenza_citta.setText(person.getCitta());
        if(viaggio.getPartenza() != null){
            int m = viaggio.getPartenza().get(Calendar.MONTH) +1;
            partenza_data.setText(viaggio.getPartenza().get(Calendar.DAY_OF_MONTH) + "/"+
                    m+"/"+viaggio.getPartenza().get(Calendar.YEAR));
        }else partenza_data.setText("");

        ritorno_citta.setText(viaggio.getCity());

        if(viaggio.getRitorno() != null){
            int m = viaggio.getRitorno().get(Calendar.MONTH) +1;
            ritorno_data.setText(viaggio.getRitorno().get(Calendar.DAY_OF_MONTH) + "/"+
                    m+"/"+viaggio.getRitorno().get(Calendar.YEAR));
        }else ritorno_data.setText("");

        arte.setText(": "+viaggio.getArte().size()+" monumenti");
        shopping.setText(": "+viaggio.getShopping().size()+" negozi");
        ristoranti.setText(": "+viaggio.getRistoranti().size()+" ristoranti");
        sport.setText(": "+viaggio.getSport().size()+" attrazioni");
        budget.setText(viaggio.getBudget()+" euro");
        if (!viaggio.getAlloggio().equals("Scegli il tipo di alloggio")){
            alloggio.setText(": "+viaggio.getAlloggio());
        }else alloggio.setText(": ");


        for (String s: viaggio.getAmici()){
            if (s.equals("@maryjanewatson")){
                amico1.setVisibility(View.VISIBLE);
            }

            if (s.equals("@markruffalo")){
                amico2.setVisibility(View.VISIBLE);
            }
        }

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showAddFriends = new Intent(ConfermaTotale.this, AddFriends.class);
                showAddFriends.putExtra(Home.TRIP, viaggio);
                showAddFriends.putExtra(Register.PERSONA,person);
                startActivity(showAddFriends);
            }
        });

        annulla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDelete();
            }
        });

        conferma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                person.getViaggi().add(viaggio);    //Aggiunto il viaggio
                Intent showHome = new Intent(ConfermaTotale.this, Home.class);
                showHome.putExtra(Register.PERSONA,person);
                startActivity(showHome);
            }
        });
    }

    public void showDelete(){

        dialog_delete.setContentView(R.layout.delete_viaggio);
        delete_subtitle = dialog_delete.findViewById(R.id.delete_subtitle);
        delete_subtitle.setText("Vuoi davvero eliminare \nil viaggio pianificato?");

        delete_viaggio = dialog_delete.findViewById(R.id.delete_all);
        undo = dialog_delete.findViewById(R.id.undo);

        delete_viaggio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showHome = new Intent(ConfermaTotale.this, Home.class);
                showHome.putExtra(Register.PERSONA,person);
                startActivity(showHome);
            }
        });

        undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_delete.dismiss();
            }
        });

        dialog_delete.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog_delete.show();
    }
}
