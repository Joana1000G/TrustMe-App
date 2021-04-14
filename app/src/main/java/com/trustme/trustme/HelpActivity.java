package com.trustme.trustme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HelpActivity extends AppCompatActivity {

    private Button btnPeople;
    private Button btnInstitutions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        //Interacción para navegar a otra pantalla
        btnPeople = findViewById(R.id.btnPeople);
        btnInstitutions = findViewById(R.id.btnInstitutions);

        //Asignación de evento
        btnPeople.setOnClickListener(this::navigateToPeople);
        btnInstitutions.setOnClickListener(this::navigateToInstitutions);
    }

    private void navigateToPeople(View view) {
        Intent intentPeople = new Intent(HelpActivity.this,PeopleActivity.class);
        startActivity(intentPeople);
    }

    private void navigateToInstitutions(View view) {
      Intent intentInstitutions =
              new Intent(HelpActivity.this,InstitutionsActivity.class);
      startActivity(intentInstitutions);
    }
}