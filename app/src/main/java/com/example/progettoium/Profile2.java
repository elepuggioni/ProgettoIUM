package com.example.progettoium;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.Calendar;

public class Profile2 extends AppCompatActivity {
    TextView username, bio, citta, completed_trips, friends;
    TextView titolo_viaggio, date_viaggio;
    Person person;
    Button goHome, editProfile, notifiche;
    LinearLayout viaggio_creato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile2);
        //Transizioni tra le activity
        overridePendingTransition(0,0);
        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(Register.PERSONA);

        if(obj instanceof Person){
            person = (Person) obj;
        }else {
            person = new Person();
        }

        username = findViewById(R.id.username_profile);
        citta = findViewById(R.id.profile_citta);
        bio = findViewById(R.id.profile_bio);
        goHome = findViewById(R.id.goHome2);
        editProfile = findViewById(R.id.edit_profile);
        completed_trips = findViewById(R.id.completed_trips);
        friends = findViewById(R.id.friends);
        notifiche = findViewById(R.id.notification);
        titolo_viaggio = findViewById(R.id.titolo_viaggio);
        date_viaggio = findViewById(R.id.date_viaggio);
        viaggio_creato = findViewById(R.id.viaggio_creato);

        if (person.getUsername().length() != 0){
            username.setText("@"+person.getUsername());
        }else{
            username.setText("@username");
        }

        if (person.getCitta().length() != 0){
            citta.setText(person.getCitta());
        }else{
            citta.setText("La tua città");
        }

        if (person.getBio().length() != 0){
            bio.setText(person.getBio());
        }else{
            bio.setText("Questa è la tua bio");
        }

        int i = person.getViaggi().size();
        if (i>0){
            Trip viaggio = person.getViaggi().get(i-1);     //Si prende sempre l'ultimo viaggio fatto
            titolo_viaggio.setText(viaggio.getCity());

            String partenza = " ";
            if(viaggio.getPartenza()!= null){
                int m = viaggio.getPartenza().get(Calendar.MONTH) +1;
                partenza = viaggio.getPartenza().get(Calendar.DAY_OF_MONTH) + "/"+
                        m+"/"+viaggio.getPartenza().get(Calendar.YEAR);
            }

            String ritorno = " ";
            if (viaggio.getRitorno()!= null){
                int m = viaggio.getRitorno().get(Calendar.MONTH) +1;
                 ritorno = viaggio.getRitorno().get(Calendar.DAY_OF_MONTH) + "/"+
                        m+"/"+viaggio.getRitorno().get(Calendar.YEAR);
            }
            date_viaggio.setText(partenza+" - "+ ritorno);
        }

        goHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showHome = new Intent(Profile2.this, Home.class);
                showHome.putExtra(Register.PERSONA,person);
                startActivity(showHome);
            }
        });

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showEditProfile = new Intent(Profile2.this, EditProfile.class);
                showEditProfile.putExtra(Register.PERSONA,person);
                startActivity(showEditProfile);
            }
        });

        completed_trips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent showProfile = new Intent(Profile2.this, Profile.class);
                    showProfile.putExtra(Register.PERSONA, person);
                    startActivity(showProfile);
            }
        });

        friends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent showProfile3 = new Intent(Profile2.this, Profile3.class);
                    showProfile3.putExtra(Register.PERSONA, person);
                    startActivity(showProfile3);
                }
        });

        notifiche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showNotifications = new Intent(Profile2.this, Notifiche.class);
                showNotifications.putExtra(Register.PERSONA, person);
                startActivity(showNotifications);
            }
        });

        viaggio_creato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent showEdit = new Intent(Profile2.this, EditTrip.class);
                showEdit.putExtra(Register.PERSONA, person);
                startActivity(showEdit);
            }
        });

    }
}
