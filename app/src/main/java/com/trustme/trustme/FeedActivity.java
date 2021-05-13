package com.trustme.trustme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FeedActivity extends AppCompatActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener,
        HistoryAdapter.OnItemClickListener {

    public static final String KEY_HISTORY_ID = "historyId";
    private RecyclerView recyclerListHistory;
    private ArrayList<History> listHistory;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference historyReference;

    private HistoryAdapter adapter;

    private BottomNavigationView menuNavigation;

    private FloatingActionButton btnWriteHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        firebaseDatabase = FirebaseDatabase.getInstance();

        recyclerListHistory = findViewById(R.id.recyclerListHistory);

        LinearLayoutManager acomodador = new LinearLayoutManager(this);
        recyclerListHistory.setLayoutManager(acomodador);

        listHistory = new ArrayList<>();

        adapter = new HistoryAdapter(listHistory, this);

        recyclerListHistory.setAdapter(adapter);
        getHistory();


        btnWriteHistory = findViewById(R.id.btnWriteHistory);
        btnWriteHistory.setOnClickListener(this::navigateToWriteHistory);

        menuNavigation = findViewById(R.id.bottomNavigationViewFeed);
        menuNavigation.setOnNavigationItemSelectedListener(this);
    }

    private void getHistory() {
        historyReference = firebaseDatabase.getReference("histories");

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterable<DataSnapshot> snapshots = snapshot.getChildren();
                for(DataSnapshot data : snapshots) {
                    adapter.addHistory(data.getValue(History.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        historyReference.addValueEventListener(valueEventListener);
    }

    private void navigateToWriteHistory(View view) {
        Intent intentWriteHistory = new Intent(FeedActivity.this,
                WriteHistoryActivity.class);
        startActivity(intentWriteHistory);
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
        Intent intentFeed = new Intent(FeedActivity.this,FeedActivity.class);
        startActivity(intentFeed);
    }
    public void navigateToHelp() {
        Intent intentHelp  = new Intent(FeedActivity.this,HelpActivity.class);
        startActivity(intentHelp);
    }
    public void navigateToMeter() {
        Intent intentMeter = new
                Intent(FeedActivity.this,ViolentMeterActivity.class);
        startActivity(intentMeter);
    }
    public void navigateToDonate(){
        Intent intentDonate = new Intent(FeedActivity.this,DonationActivity.class);
        startActivity(intentDonate);
    }

    @Override
    public void onItemClick(History history) {
        String historyId = history.getId();
        Intent intentOneHistory = new Intent(FeedActivity.this,
                OneHistoryActivity.class);
        intentOneHistory.putExtra(KEY_HISTORY_ID, historyId);
        startActivity(intentOneHistory);

    }

    private void showCommentsDialog(String id) {
        ItemCommentsDetailDialogFragment.newInstance(id).show(getSupportFragmentManager(),
                "comments_dialog");
    }


    private void navigateToComments(String historyId) {
        Intent intentComments = new Intent (FeedActivity.this,
                ItemCommentsDetailDialogFragment.class);
        //Pasamos un dato al intento de abrir la pantalla de tipo String, el Id de la historia
        intentComments.putExtra(KEY_HISTORY_ID, historyId);
        startActivity(intentComments);
    }

}