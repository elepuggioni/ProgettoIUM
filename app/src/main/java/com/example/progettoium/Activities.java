package com.example.progettoium;

import android.content.Intent;
import android.graphics.BlendMode;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class Activities extends AppCompatActivity {
    public static final String CATEGORIA = "Categoria";

    Button goBack, continua;
    TextView titleCity;
    ImageView arte, sport, shopping, risto;
    LinearLayout ar,sp,sh,ri;

    Trip viaggio;
    Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attrazioni);

        goBack = findViewById(R.id.goHome);
        continua = findViewById(R.id.continuaPlan2);
        titleCity = findViewById(R.id.titlePlan);
        arte = findViewById(R.id.arteImage);
        sport = findViewById(R.id.sportImage);
        shopping = findViewById(R.id.shoppingImage);
        risto = findViewById(R.id.ristoImage);
        ar = findViewById(R.id.act_arte);
        sp = findViewById(R.id.act_sport);
        sh = findViewById(R.id.act_shopping);
        ri = findViewById(R.id.act_ristoranti);

        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(Home.TRIP);

        if(obj instanceof Trip){
            viaggio = (Trip) obj;
        }else {
            viaggio = new Trip();
        }

        Serializable obj2 = intent.getSerializableExtra(Register.PERSONA);

        if(obj2 instanceof Person){
            person = (Person) obj2;
        }else {
            person = new Person();
        }

        boolean flag = intent.getBooleanExtra(AttractionsCheck.FLAG,false);

        titleCity.setText(viaggio.getCity());

        //scelta diverse categorie; viene passato un valore all'activity
        arte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ar.setBackgroundColor(getResources().getColor(R.color.yellow));
                Intent showArte = new Intent(Activities.this, FindAttractions.class);
                showArte.putExtra(Home.TRIP, viaggio);
                showArte.putExtra(Activities.CATEGORIA, 1);
                startActivity(showArte);
            }
        });

        sport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp.setBackgroundColor(getResources().getColor(R.color.yellow));
                Intent showSport = new Intent(Activities.this, FindAttractions.class);
                showSport.putExtra(Home.TRIP, viaggio);
                showSport.putExtra(Activities.CATEGORIA, 2);
                startActivity(showSport);
            }
        });

        shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sh.setBackgroundColor(getResources().getColor(R.color.yellow));
                Intent showShopping = new Intent(Activities.this, FindAttractions.class);
                showShopping.putExtra(Home.TRIP, viaggio);
                showShopping.putExtra(Activities.CATEGORIA, 3);
                startActivity(showShopping);
            }
        });

        risto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ri.setBackgroundColor(getResources().getColor(R.color.yellow));
                Intent showRisto = new Intent(Activities.this, FindAttractions.class);
                showRisto.putExtra(Home.TRIP, viaggio);
                showRisto.putExtra(Activities.CATEGORIA, 4);
                startActivity(showRisto);
            }
        });

        if (flag){
            continua.setText("continua");
        }else continua.setText("non adesso...");

        continua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showContinua = new Intent(Activities.this, AddFriends.class);
                showContinua.putExtra(Home.TRIP, viaggio);
                showContinua.putExtra(Register.PERSONA,person);
                startActivity(showContinua);
            }
        });

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showHome = new Intent(Activities.this, PlanTrip.class);
                showHome.putExtra(Home.TRIP, viaggio);
                showHome.putExtra(Register.PERSONA,person);
                startActivity(showHome);
            }
        });
    }
}
