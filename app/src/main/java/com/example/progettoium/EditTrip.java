package com.example.progettoium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;

public class EditTrip extends AppCompatActivity {

    Person person;
    TextView titolo;
    ImageView back;
    Button salva;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_trip);
        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(Register.PERSONA);

        if(obj instanceof Person){
            person = (Person) obj;
        }else {
            person = new Person();
        }

        int i = person.getViaggi().size();
        if(i>0){
            titolo = findViewById(R.id.edit_titolo_citta);
            titolo.setText(person.getViaggi().get(i-1).getCity());
        }

        back = findViewById(R.id.edit_back);
        salva = findViewById(R.id.edit_salva);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent showProfile2 = new Intent(EditTrip.this, Profile2.class);
                showProfile2.putExtra(Register.PERSONA, person);
                startActivity(showProfile2);
            }
        });

        salva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent showProfile2 = new Intent(EditTrip.this, Profile2.class);
                showProfile2.putExtra(Register.PERSONA, person);
                startActivity(showProfile2);
            }
        });
    }
}
