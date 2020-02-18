package com.example.progettoium;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.io.Serializable;

public class Notifiche extends AppCompatActivity {
    Person person;
    Button goBack, accetta, rifiuta;
    LinearLayout notifica;
    Dialog invitation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifiche);

        invitation = new Dialog(this);

        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(Register.PERSONA);

        if(obj instanceof Person){
            person = (Person) obj;
        }else {
            person = new Person();
        }

        notifica = findViewById(R.id.notifica);
        goBack = findViewById(R.id.back_arrow);

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showProfile = new Intent(Notifiche.this, Profile.class);
                showProfile.putExtra(Register.PERSONA, person);
                startActivity(showProfile);
            }
        });

        notifica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInvitation();
            }
        });
    }

    public void showInvitation(){
        invitation.setContentView(R.layout.notification);
        rifiuta = invitation.findViewById(R.id.rifiuta);
        accetta = invitation.findViewById(R.id.accetta);

        rifiuta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifica.setBackgroundColor(getResources().getColor(R.color.design_default_color_background));
                invitation.dismiss();
            }
        });

        accetta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifica.setBackgroundColor(getResources().getColor(R.color.design_default_color_background));
                invitation.dismiss();
            }
        });
        invitation.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        invitation.show();
    }
}
