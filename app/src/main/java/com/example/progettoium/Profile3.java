package com.example.progettoium;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class Profile3 extends AppCompatActivity {
    TextView username, bio, citta, completed_trips, planned_trips, delete_subtitle;
    Person person;
    Button goHome, editProfile, notifiche, ok, undo;
    LinearLayout mary2, mark;
    ImageButton mary1;
    ImageButton remove_friend;
    Dialog remove_dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile3);
        //Transizioni tra le activity
        overridePendingTransition(0,0);
        remove_dialog = new Dialog(this);

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
        planned_trips = findViewById(R.id.planned_trips);
        notifiche = findViewById(R.id.notification);
        mary1 = findViewById(R.id.mary1);
        mary2 = findViewById(R.id.mary2);
        mark = findViewById(R.id.ruffalo);
        remove_friend = findViewById(R.id.friend_remove);

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
                Intent showHome = new Intent(Profile3.this, Home.class);
                showHome.putExtra(Register.PERSONA,person);
                startActivity(showHome);
            }
        });

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showEditProfile = new Intent(Profile3.this, EditProfile.class);
                showEditProfile.putExtra(Register.PERSONA,person);
                startActivity(showEditProfile);
            }
        });

        completed_trips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent showProfile = new Intent(Profile3.this, Profile.class);
                    showProfile.putExtra(Register.PERSONA, person);
                    startActivity(showProfile);
                }
        });

        planned_trips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent showProfile2 = new Intent(Profile3.this, Profile2.class);
                    showProfile2.putExtra(Register.PERSONA, person);
                    startActivity(showProfile2);
            }
        });

        notifiche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showNotifications = new Intent(Profile3.this, Notifiche.class);
                showNotifications.putExtra(Register.PERSONA, person);
                startActivity(showNotifications);
            }
        });

        mary1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent showFriend = new Intent(Profile3.this, FriendProfile.class);
                showFriend.putExtra(Register.PERSONA, person);
                startActivity(showFriend);
            }
        });

        mary2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent showFriend = new Intent(Profile3.this, FriendProfile.class);
                showFriend.putExtra(Register.PERSONA, person);
                startActivity(showFriend);
            }
        });

        remove_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRemove();
            }
        });
    }
    public void showRemove(){
        remove_dialog.setContentView(R.layout.delete_viaggio);
        delete_subtitle = remove_dialog.findViewById(R.id.delete_subtitle);
        delete_subtitle.setText("Vuoi davvero rimuovere Mark Ruffalo\n dai tuoi amici?");
        undo = remove_dialog.findViewById(R.id.undo);
        ok = remove_dialog.findViewById(R.id.delete_all);

        undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove_dialog.dismiss();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mark.removeAllViews();
                remove_dialog.dismiss();
            }
        });

        remove_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        remove_dialog.show();
    }
}
