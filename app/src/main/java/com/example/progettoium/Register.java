package com.example.progettoium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class Register extends AppCompatActivity {
    public static final String PERSONA = "Persona";

    EditText username, password, confPassword, citta;
    Button iscriviti, goLogin;
    Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.regUsername);
        password = findViewById(R.id.regPassword);
        confPassword =findViewById(R.id.regConfPassword);
        citta = findViewById(R.id.regCitta);
        iscriviti = findViewById(R.id.regButton);
        goLogin = findViewById(R.id.register_goLogin);
        person = new Person();

        iscriviti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CheckInput()){
                    UpdatePerson();
                    Intent showLogin = new Intent(Register.this, MainActivity.class);
                    showLogin.putExtra(Register.PERSONA,person);
                    startActivity(showLogin);
                }
            }
        });

        goLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showHome = new Intent(Register.this, MainActivity.class);
                showHome.putExtra(Register.PERSONA,person);
                startActivity(showHome);
            }
        });
    }

    private boolean CheckInput() {
        int errori = 0;

        if (username.getText() == null || username.getText().length() == 0){
            username.setError("Inserire lo username");
            errori ++;
        }else {
            username.setError(null);
        }

        if (password.getText() == null || password.getText().length() == 0){
            password.setError("Inserire la password");
            errori ++;
        }else {
            password.setError(null);
        }

        if(citta.getText() == null || citta.getText().length() == 0){
            citta.setError("Inserire la citta'");
            errori++;
        }else {
            citta.setError(null);
        }

        if(!password.getText().toString().equals(confPassword.getText().toString()) ){
            confPassword.setError("Le due password non coincidono");
            errori++;
        }else {
            confPassword.setError(null);
        }

        return errori ==0;
    }

    protected void UpdatePerson(){
        this.person.setUsername(this.username.getText().toString());
        this.person.setPassword(this.password.getText().toString());
        this.person.setCitta(this.citta.getText().toString());
    }
}
