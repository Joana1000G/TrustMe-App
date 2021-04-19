package com.trustme.trustme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PeopleActivity extends AppCompatActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener, PeopleAdapter.OnItemClickListener {

    private RecyclerView recyclerListPeople;
    private ArrayList<People>  listPeople;

    private PeopleAdapter adapter;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference peopleReference;

    private BottomNavigationView menuNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);

        firebaseDatabase = FirebaseDatabase.getInstance();

        recyclerListPeople = findViewById(R.id.recyclerListPeople);

        LinearLayoutManager acomodador = new LinearLayoutManager(this);
        recyclerListPeople.setLayoutManager(acomodador);

        listPeople = new ArrayList<>();

        People florNayeliGonzálezAndrade = new People(1, "Flor Nayeli Gonzáles Andrade",
                "Psychologist, Individual, group, couple and family therapy, as " +
                        "well as specialized", "38485026");
        People rosalinaRoblesGarcía = new People(2, "Rosalina Robles García",
                "Psychologist, Lic. Psychology;" +
                "Price per session: $ 299 - $ 499, Sliding scale according to income: Yes" +
                "We will take your process with short, simple and effective methods using NLP, " +
                "Kinesiology, Mindfulness techniques, all focused on achieving your well-being.",
                "8002832805");

        //Guardar en la lista
        listPeople.add(florNayeliGonzálezAndrade);
        listPeople.add(rosalinaRoblesGarcía);

        adapter = new PeopleAdapter(listPeople, this);

        recyclerListPeople.setAdapter(adapter);
        getPeople();



        menuNavigation = findViewById(R.id.bottomNavigationViewPeople);
        menuNavigation.setOnNavigationItemSelectedListener(this);
    }

    private void getPeople() {
        peopleReference = firebaseDatabase.getReference("people");
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterable<DataSnapshot> snapshots = snapshot.getChildren();
                for(DataSnapshot data : snapshots) {
                    adapter.addPeople(data.getValue(People.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        //Registrar para que escuche los cambios
        peopleReference.addValueEventListener(valueEventListener);
    }



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
        Intent intentFeed = new Intent(PeopleActivity.this,FeedActivity.class);
        startActivity(intentFeed);
    }
    public void navigateToHelp() {
        Intent intentHelp  = new Intent(PeopleActivity.this,HelpActivity.class);
        startActivity(intentHelp);
    }
    public void navigateToMeter() {
        Intent intentMeter = new
                Intent(PeopleActivity.this,ViolentMeterActivity.class);
        startActivity(intentMeter);
    }
    public void navigateToDonate(){
        Intent intentDonate = new Intent(PeopleActivity.this,DonationActivity.class);
        startActivity(intentDonate);
    }

    @Override
    public void onItemClick(People people) {
        
    }
}