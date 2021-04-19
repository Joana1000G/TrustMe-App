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

public class InstitutionsActivity extends AppCompatActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener,
        InstitutionsAdapter.OnItemClickListener {

    private RecyclerView recyclerListInstitutions;
    private ArrayList<Institutions> listInstitutions;

    private InstitutionsAdapter adapter;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference institutionsReference;

    private BottomNavigationView menuNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_institutions);

        firebaseDatabase = FirebaseDatabase.getInstance();

        recyclerListInstitutions = findViewById(R.id.recyclerListInstitutions);

        LinearLayoutManager acomodador = new LinearLayoutManager(this);
        recyclerListInstitutions.setLayoutManager(acomodador);

        listInstitutions = new ArrayList<>();

        Institutions justiceCenterWomen = new Institutions(1,
                "Justice Center Women", "Open 24 hours",
                3330305450L,
                "Calle Alvaro Alcazar, Jardines Alcalde, 44298 Guadalajara, Jal, Mexico.");
        Institutions municipalInstituteOfWomen = new Institutions(2,
                "Municipal Institute of Women",
                "Monday to Friday from 8:30 a.m. to 3:00 p.m.",
                4773111832L,
                "Avenida Olímpica 1603, corner of Tula street Blue Water Cologne");

        //Guardar en la lista
        listInstitutions.add(justiceCenterWomen);
        listInstitutions.add(municipalInstituteOfWomen);

        adapter = new InstitutionsAdapter(listInstitutions, this);

        recyclerListInstitutions.setAdapter(adapter);
        getInstitutions();


        menuNavigation = findViewById(R.id.bottomNavigationViewInstitutions);
        menuNavigation.setOnNavigationItemSelectedListener(this);
    }

    private void getInstitutions() {
        institutionsReference = firebaseDatabase.getReference("Institutions");
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterable<DataSnapshot> snapshots = snapshot.getChildren();
                for(DataSnapshot data : snapshots) {
                    adapter.addInstitutions(data.getValue(Institutions.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        //Registrar para que escuche los cambios
        institutionsReference.addValueEventListener(valueEventListener);
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
        Intent intentFeed = new Intent(InstitutionsActivity.this,FeedActivity.class);
        startActivity(intentFeed);
    }
    public void navigateToHelp() {
        Intent intentHelp  = new Intent(InstitutionsActivity.this,HelpActivity.class);
        startActivity(intentHelp);
    }
    public void navigateToMeter() {
        Intent intentMeter = new
                Intent(InstitutionsActivity.this,ViolentMeterActivity.class);
        startActivity(intentMeter);
    }
    public void navigateToDonate(){
        Intent intentDonate = new Intent(InstitutionsActivity.this,DonationActivity.
                class);
        startActivity(intentDonate);
    }

    @Override
    public void onItemClick(Institutions institutions) {
        int institutionsId = institutions.getId();
        Intent intentInformation = new Intent(InstitutionsActivity.this,
                InstitutionInformationActivity.class);
        startActivity(intentInformation);

    }
}