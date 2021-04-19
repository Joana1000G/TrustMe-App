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

        History relationshipOfAbuse = new History(1,"User1965",
                "2 hours ago", "Degree 2", "Category Mistreatment",
                "Relationship of abuse",
                "About 1 year ago I broke up with my boyfriend for his mistreatment " +
                        "of me, both psychological and physical for more than 1 year later I " +
                        "realized the atmosphere so negative that there was in");
        History myChildrenAreInDanger  = new History(2, "User1966", "3 hours ago",
                "Degree 5", "Category Mistreatment",
                "My children are in danger", "I have been married to " +
                "my husband for 15 years, we have three daughters, but for some time things " +
                "have not been going well, he beats us, and I am afraid that he will hurt " +
                "them seriously");

        listHistory.add(relationshipOfAbuse);
        listHistory.add(myChildrenAreInDanger);

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
        int historyId = history.getId();
        Intent intentOneHistory = new Intent(FeedActivity.this,
                OneHistoryActivity.class);
        startActivity(intentOneHistory);

    }

}