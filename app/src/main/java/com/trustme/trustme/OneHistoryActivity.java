package com.trustme.trustme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OneHistoryActivity extends AppCompatActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener {

    private String historyId;

    private Button btnCommentsOneHistory;

    private BottomNavigationView menuNavigation;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference historyReference;

    private TextView txtUserStory;
    private TextView txtDateStory;
    private TextView txtDegree;
    private TextView txtCategory;
    private TextView txtTitleStory;
    private TextView txtTextStory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_history);

        firebaseDatabase = FirebaseDatabase.getInstance();

        //Por envio de valores <---
        //Stores

        //Obtenemos el intent de la pantalla de donde se abrio
        Intent intent = getIntent();

        //Obtener el valor por medio de su clave y indicado del tipo dato
        historyId = intent.getStringExtra(FeedActivity.KEY_HISTORY_ID);

        txtUserStory = findViewById(R.id.txtUserStory);
        txtDateStory = findViewById(R.id.txtDateStory);
        txtDegree = findViewById(R.id.txtDegree);
        txtCategory = findViewById(R.id.txtCategory);
        txtTitleStory = findViewById(R.id.txtTitleStory);
        txtTextStory = findViewById(R.id.txtTextHistory);




        btnCommentsOneHistory = findViewById(R.id.btnCommentsOneHistory);
        btnCommentsOneHistory.setOnClickListener(this::navigateToCommentsFragment);

        menuNavigation = findViewById(R.id.bottomNavigationViewOneHistory);
        menuNavigation.setOnNavigationItemSelectedListener(this);
    }

    private void navigateToCommentsFragment(View view) {
       //Crear un objeto para la ventana de comentarios que se va a mostrar
        ItemCommentsDetailDialogFragment fragmentWindow =
                ItemCommentsDetailDialogFragment.newInstance(historyId);

        //Obtenemos un objeto administrador de Fragmentos
        //Significa que es un objeto que quita y pone fragmentos (ventanas)
        FragmentManager fragmentManager = getSupportFragmentManager();

        //Mostramos la ventana fragmento por medio de decirle quien la administra
        // y como se llama
        fragmentWindow.show(fragmentManager, "Comments_dialog");
    }

    private void getHistory(String historyId) {

        historyReference = firebaseDatabase.getReference("histories").child(historyId);

        historyReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                History history = snapshot.getValue(History.class);

                txtUserStory.setText(history.getUserStory());
                txtDateStory.setText(history.getDateStory());
                txtDegree.setText(history.getDegree());
                txtCategory.setText(history.getCategory());
                txtTitleStory.setText(history.getTitleStory());
                txtTextStory.setText(history.getTextHistory());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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