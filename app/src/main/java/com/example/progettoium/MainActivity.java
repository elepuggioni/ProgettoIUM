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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        provamaps = findViewById(R.id.provamaps);
        username = findViewById(R.id.username_input);
        password = findViewById(R.id.password_input);
        register= findViewById(R.id.register);

        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(Register.PERSONA);

        if(obj instanceof Person){
            person = (Person) obj;
        }else {
            person = new Person();
        }

        provamaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CheckInput()){
                    Intent showHome = new Intent(MainActivity.this, Home2.class);
                    showHome.putExtra(Register.PERSONA, person);
                    startActivity(showHome);
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showRegister = new Intent(MainActivity.this, Register.class);
                showRegister.putExtra(Register.PERSONA,person);
                startActivity(showRegister);
            }
        });
    }

    private boolean CheckInput() {
        int errori = 0;

        if (username.getText() == null || username.getText().length() == 0
                || !username.getText().toString().equals(person.getNomeCognome())){
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
