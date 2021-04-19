package com.trustme.trustme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WriteHistoryActivity extends AppCompatActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener {

    private EditText editTextTitleStory;
    private Button btnDegreeViolence;
    private Spinner spinnerCategories;
    private EditText editTextWriteStory;
    private Button btnShareHistory;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference historyReference;

    private BottomNavigationView menuNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_history);

        firebaseDatabase = FirebaseDatabase.getInstance();

        editTextTitleStory = findViewById(R.id.editTextTitleStory);
        btnDegreeViolence = findViewById(R.id.btnCommentsOneHistory);
        spinnerCategories = findViewById(R.id.spinnerCategories);
        editTextWriteStory = findViewById(R.id.editTextWriteStory);
        btnShareHistory = findViewById(R.id.btnShareHistory);

        btnShareHistory.setOnClickListener(this::writeHistory);

        menuNavigation = findViewById(R.id.bottomNavigationViewWrite);
        menuNavigation.setOnNavigationItemSelectedListener(this);
    }

    public void writeHistory(View view) {
        String titleHistory = editTextTitleStory.getText().toString().trim();
        String degree = "Degree 2";
        String category = (String) spinnerCategories.getSelectedItem();
        String writeHistory = editTextWriteStory.getText().toString().trim();


        History history = new History( degree, category, titleHistory, writeHistory);

        createHistoryInDatabase(history);
    }

    private void createHistoryInDatabase(History history) {
        historyReference = firebaseDatabase.getReference("histories");

        DatabaseReference  newHistoryRef = historyReference.push();

        String key = newHistoryRef.getKey();

        historyReference.child(key).setValue(history)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    showMessage("History was successfully saved");
                    navigateToFeed();
                } else {
                    task.getException().printStackTrace();
                    showMessage("Failed to save history");
                }
            }
        });
    }

    private void showMessage(String message) {
        Toast.makeText(this, message,Toast.LENGTH_SHORT).show();
    }

    private void navigateToFeed(View view) {
        Intent intent = new Intent(WriteHistoryActivity.this, FeedActivity.class);
        startActivity(intent);
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
        Intent intentFeed = new Intent(WriteHistoryActivity.this,FeedActivity.class);
        startActivity(intentFeed);
    }
    public void navigateToHelp() {
        Intent intentHelp  = new Intent(WriteHistoryActivity.this,HelpActivity.class);
        startActivity(intentHelp);
    }
    public void navigateToMeter() {
        Intent intentMeter = new
                Intent(WriteHistoryActivity.this,ViolentMeterActivity.class);
        startActivity(intentMeter);
    }
    public void navigateToDonate(){
        Intent intentDonate = new Intent(WriteHistoryActivity.this,DonationActivity.class);
        startActivity(intentDonate);
    }
}