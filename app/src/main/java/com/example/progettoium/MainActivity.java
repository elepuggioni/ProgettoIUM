package com.example.progettoium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {
    Person person;
    Button provamaps;
    TextView username, password, register;

    //variabili di debug
    static final String username_debug = "debug";
    static final String password_debug = "debug";
    static final boolean DEBUG = true;
    TextView debug_profile, debug_plan, debug_edittrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        provamaps = findViewById(R.id.provamaps);
        username = findViewById(R.id.username_input);
        password = findViewById(R.id.password_input);
        register = findViewById(R.id.register);

        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(Register.PERSONA);

        if (obj instanceof Person) {
            person = (Person) obj;
        } else {
            person = new Person();
        }

        provamaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CheckInput()) {
                    Intent showHome = new Intent(MainActivity.this, Home.class);
                    showHome.putExtra(Register.PERSONA, person);
                    startActivity(showHome);
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showRegister = new Intent(MainActivity.this, Register.class);
                showRegister.putExtra(Register.PERSONA, person);
                startActivity(showRegister);
            }
        });

        //shortcut al profilo
        if (DEBUG) {
            debug_profile = findViewById(R.id.debug_profile);
            debug_profile.setText(new String("Profile"));
            debug_profile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent showProfile = new Intent(MainActivity.this, Profile.class);
                    showProfile.putExtra(Register.PERSONA, person);
                    startActivity(showProfile);
                }
            });

            debug_plan = findViewById(R.id.debug_plan);
            debug_plan.setText(new String("Pianificazione"));
            debug_plan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent showPlan = new Intent(MainActivity.this, PlanTrip.class);
                    //showProfile.putExtra(Register.PERSONA, person);
                    startActivity(showPlan);
                }
            });

            debug_edittrip = findViewById(R.id.debug_edittrip);
            debug_edittrip.setText(new String("Modifica viaggio"));
            debug_edittrip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent showPlan = new Intent(MainActivity.this, EditTrip.class);
                    //showProfile.putExtra(Register.PERSONA, person);
                    startActivity(showPlan);
                }
            });
        }
    }
    private boolean CheckInput() {
        int errori = 0;

        //se la modalità debug è attiva e inserisci le credenziali di debug, per saltare la registrazione
        if(DEBUG && username.getText().toString().equals(username_debug) && password.getText().toString().equals(password_debug))
            return true;

        if (username.getText() == null || username.getText().length() == 0
                || !username.getText().toString().equals(person.getUsername())){
            username.setError("Username non corretto");
            errori++;
        }else {
            username.setError(null);
        }

        if(password.getText() == null || password.getText().length() == 0
                || !password.getText().toString().equals(person.getPassword()) ){

            password.setError("Password non corretta");
            errori++;
        }else {
            password.setError(null);
        }

        return errori == 0;
    }

}
