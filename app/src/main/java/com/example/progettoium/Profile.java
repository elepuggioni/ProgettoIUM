package com.example.progettoium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.io.Serializable;

public class Profile extends AppCompatActivity {
    TextView username, bio, citta;
    Person person;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

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

        username.setText("@"+person.getUsername());
        citta.setText(person.getCitta());

        if (person.getBio()!= null){
            bio.setText(person.getBio());
        }else{
            bio.setText("Questa è la tua bio");
        }
    }
}
