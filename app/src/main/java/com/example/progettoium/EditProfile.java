package com.example.progettoium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.Serializable;

public class EditProfile extends AppCompatActivity {
    ImageView backArrow, cambiaFoto, cambiaCopertina;
    EditText username, bio, citta, password, confermaPassword;
    Button salva;
    Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(Register.PERSONA);

        if(obj instanceof Person){
            person = (Person) obj;
        }else {
            person = new Person();
        }

        backArrow = findViewById(R.id.back_arrow);
        cambiaFoto = findViewById(R.id.foto_icona);
        cambiaCopertina = findViewById(R.id.foto_icona_copertina);
        username = findViewById(R.id.input_name);
        bio = findViewById(R.id.input_bio);
        citta = findViewById(R.id.input_citta);
        password = findViewById(R.id.input_nuova_password);
        confermaPassword = findViewById(R.id.input_conferma_password);
        salva = findViewById(R.id.salva);

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showProfile = new Intent(getApplicationContext(), Profile.class);
                startActivity(showProfile);
            }
        });

        cambiaFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cambia foto :D
            }
        });

        cambiaCopertina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cambia copertina :DD
            }
        });

        salva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CheckInput()){
                    UpdatePerson();
                    Intent showProfile = new Intent(getApplicationContext(), Profile.class);
                    showProfile.putExtra(Register.PERSONA,person);
                    startActivity(showProfile);
                }
            }
        });
    }



    private boolean CheckInput(){
        int errori = 0;

        if (username.getText() == null || username.getText().length() == 0){
            username.setError("Inserire lo username");
            errori ++;
        }else {
            username.setError(null);
        }

        if (bio.getText() == null || bio.getText().length() == 0){
            bio.setError("La bio non può essere vuota");
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

        if(!password.getText().toString().equals(confermaPassword.getText().toString()) ){
            confermaPassword.setError("Le due password non coincidono");
            errori++;
        }else {
            confermaPassword.setError(null);
        }

        return errori ==0;
    }

    protected void UpdatePerson(){
        this.person.setUsername(this.username.getText().toString());
        this.person.setPassword(this.password.getText().toString());
        this.person.setCitta(this.citta.getText().toString());
        this.person.setBio(this.bio.getText().toString());
    }
}
