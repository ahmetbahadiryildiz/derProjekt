package com.greemlock.derprojekt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(view -> finish());

        Button buttonLogIn = findViewById(R.id.buttonLogIn);
        buttonLogIn.setOnClickListener(view ->{

            EditText editTextEmail = findViewById(R.id.editTextEmail);
            EditText editTextPassword = findViewById(R.id.editTextPassword);

            String userEmail = editTextEmail.getText().toString();
            String userPassword = editTextPassword.getText().toString();

            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

            firebaseAuth.signInWithEmailAndPassword(userEmail,userPassword)
                .addOnCompleteListener(LoginActivity.this, task -> {

                    if (task.isSuccessful()){
                        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

                        assert firebaseUser != null;
                        if (firebaseUser.isEmailVerified()){
                            editTextEmail.setText("");
                            editTextPassword.setText("");

                            Intent loginIntent = new Intent(LoginActivity.this,BaseActivity.class);
                            loginIntent.putExtra("currentUser",firebaseUser);
                            startActivity(loginIntent);
                        }
                        else{
                            Toast.makeText(LoginActivity.this, "Please verify your account!", Toast.LENGTH_SHORT).show();

                        }
                    }
                    else{
                        Toast.makeText(LoginActivity.this, Objects.requireNonNull(task.getException()).getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        });
    }

}