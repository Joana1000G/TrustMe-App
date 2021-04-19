package com.trustme.trustme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

import android.content.Intent;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {
    //Variables
    private EditText editTextUsername;
    private EditText editTextPassword;
    private TextView textForgotPassword;
    private Button btnLogin;
    private Button btnGoToSingUp;
    private Button btnEnterWithoutAccount;

    private FirebaseAuth firebaseAuth; //API - AutAuthentication Service

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if(currentUser != null) {
            navigateToFeed();
        }

        //Inflate
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        textForgotPassword = findViewById(R.id.textForgotPassword);

        btnLogin = findViewById(R.id.btnLogin);
        btnGoToSingUp = findViewById(R.id.btnGoToSingUp);
        btnEnterWithoutAccount = findViewById(R.id.btnEnterWithoutAccount);

        //Asignación de eventos
        btnLogin.setOnClickListener(this::clickLogin);
        btnGoToSingUp.setOnClickListener(this::navigateToSingUp);
        btnEnterWithoutAccount.setOnClickListener(this::navigateToFeedWithoutAccount);
        textForgotPassword.setOnClickListener(this::clickForgotPassword);

    }

    private void clickLogin(View view) {
        //Asignación de valores iniciales a las variables, asignación de vacio
        String email = "";
        String password = "";

        //Tomar los valores de la caja de texto

        email = editTextUsername.getText().toString().trim();
        password = editTextPassword.getText().toString();

        // Operodores Lógicos
        // ! not
        // && and
        // || or
         login(email, password);


    }

    private void login(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            navigateToFeed();
                        } else {
                            Toast.makeText(LoginActivity.this, "Incorect Data",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void navigateToFeed(){
        Intent intentFeed = new Intent(LoginActivity.this,FeedActivity.class);
        startActivity(intentFeed);
    }

    private void navigateToSingUp(View view) {
        Intent intentSingUp = new Intent(LoginActivity.this,SignUpActivity.class);
        startActivity(intentSingUp);
    }

    private void navigateToFeedWithoutAccount(View view) {
        Intent intentWithoutAccount = new Intent(LoginActivity.this,
                FeedActivity.class);
        startActivity(intentWithoutAccount);
    }

    private void clickForgotPassword(View view){
        //TODO vincular la recuperación de contraseña
        Intent intentForgotPassword = new Intent();
        startActivity(intentForgotPassword);
    }

}