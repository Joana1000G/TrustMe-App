package com.trustme.trustme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HelpActivity extends AppCompatActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener{

    private Button btnPeople;
    private Button btnInstitutions;
    private BottomNavigationView menuNavigationHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        //Interacción para navegar a otra pantalla
        btnPeople = findViewById(R.id.btnPeople);
        btnInstitutions = findViewById(R.id.btnInstitutions);
        menuNavigationHelp = findViewById(R.id.bottomNavigationViewHelp);

        //Asignación de evento
        btnPeople.setOnClickListener(this::navigateToPeople);
        btnInstitutions.setOnClickListener(this::navigateToInstitutions);
        menuNavigationHelp.setOnNavigationItemSelectedListener(this);

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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.option_feed) {
            navigateToFeed();

        } else if (item.getItemId()== R.id.option_help) {
            navigateToHelp();

        } else if (item.getItemId() == R.id.option_meter) {
            navigateToMeter();

        } else if (item.getItemId() == R.id.option_donate) {
            navigateToDonate();

        }
        return true;

    }
    public void navigateToFeed() {
        Intent intentFeed = new Intent(HelpActivity.this,FeedActivity.class);
        startActivity(intentFeed);
    }
    public void navigateToHelp() {
        Intent intentHelp  = new Intent(HelpActivity.this,HelpActivity.class);
        startActivity(intentHelp);
    }
    public void navigateToMeter() {
        Intent intentMeter = new
                Intent(HelpActivity.this,ViolentMeterActivity.class);
        startActivity(intentMeter);
    }
    public void navigateToDonate(){
        Intent intentDonate = new Intent(HelpActivity.this,DonationActivity.class);
        startActivity(intentDonate);
    }
}