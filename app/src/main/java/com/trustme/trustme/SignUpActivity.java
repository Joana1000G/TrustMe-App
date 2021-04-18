package com.trustme.trustme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.CheckBox;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextPasswordSingUp;
    private EditText editTextConfirmPassword;
    private EditText editTextDateBirth;
    private CheckBox checkBoxSecurityPolitics;
    private Button btnJoin;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firebaseAuth = FirebaseAuth.getInstance(); //Recuperar una instancia del servicio

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPasswordSingUp = findViewById(R.id.editTextPasswordSingUp);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        editTextDateBirth = findViewById(R.id.editTextDateBirth);
        checkBoxSecurityPolitics = findViewById(R.id.checkBoxSecurityPolitics);
        btnJoin = findViewById(R.id.btnJoin);

        btnJoin.setOnClickListener(this::clickSingUp);
    }

    //Variables
    public void clickSingUp(View view) {
        String email = "";
        String password = "";
        String confirmPassword = "";
        String dateBirth = "";
        boolean securityPolitics = false;

        email = editTextEmail.getText().toString().trim();
        password = editTextPasswordSingUp.getText().toString().trim();
        confirmPassword = editTextConfirmPassword.getText().toString().trim();
        dateBirth = editTextDateBirth.getText().toString().trim();
        securityPolitics = checkBoxSecurityPolitics.isChecked();

        if (!email.equals("") && !password.equals("") && confirmPassword.equals(password) &&
                !dateBirth.equals("") && securityPolitics) {
            //Validación de que la contraseña tenga 6 o más caracteres
            if (password.length() >= 6) {
                if(confirmPassword.equals(password)) {
                    registerUser(email, password);
                }
            } else {
                Toast.makeText(this, "The password needs more than 6 characters",
                        Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this,"Incorrect data",Toast.LENGTH_SHORT).show();
        }

    }

    public void registerUser(String email, String password) {

        // Lógica para registrar un nuevo usuario
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() /* Contracto */{
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // Preguntamos si se registro con exito el usuario con correo y contraseña
                        if(task.isSuccessful()) {
                            navigateToFeed();
                        } else {
                            showMessage("There was a problem");
                        }
                    }
                });
    }



    public void navigateToFeed() {
        Intent intent = new Intent(SignUpActivity.this, FeedActivity.class);
        startActivity(intent);
    }


    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}