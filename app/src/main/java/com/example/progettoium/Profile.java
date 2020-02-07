package com.example.progettoium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;

public class Profile extends AppCompatActivity {
    TextView username, bio, citta;
    Person person;
    Button goHome, editProfile ;
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
        goHome = findViewById(R.id.goHome2);
        editProfile = findViewById(R.id.edit_profile);

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

        goHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showHome = new Intent(Profile.this, Home.class);
                showHome.putExtra(Register.PERSONA,person);
                startActivity(showHome);
            }
        });

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showEditProfile = new Intent(Profile.this, EditProfile.class);
                showEditProfile.putExtra(Register.PERSONA,person);
                startActivity(showEditProfile);
            }
        });
    }
}
