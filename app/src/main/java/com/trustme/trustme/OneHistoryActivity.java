package com.trustme.trustme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class OneHistoryActivity extends AppCompatActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView menuNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_history);

        menuNavigation = findViewById(R.id.bottomNavigationViewOneHistory);
        menuNavigation.setOnNavigationItemSelectedListener(this);
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
        Intent intentFeed = new Intent(OneHistoryActivity.this,FeedActivity.class);
        startActivity(intentFeed);
    }
    public void navigateToHelp() {
        Intent intentHelp  = new Intent(OneHistoryActivity.this,HelpActivity.class);
        startActivity(intentHelp);
    }
    public void navigateToMeter() {
        Intent intentMeter = new
                Intent(OneHistoryActivity.this,ViolentMeterActivity.class);
        startActivity(intentMeter);
    }
    public void navigateToDonate(){
        Intent intentDonate = new Intent(OneHistoryActivity.this,DonationActivity.class);
        startActivity(intentDonate);
    }
}