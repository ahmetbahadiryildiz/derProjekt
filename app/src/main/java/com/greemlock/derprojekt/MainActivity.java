package com.greemlock.derprojekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if(currentUser != null){
            Intent loginIntent = new Intent(MainActivity.this, BaseActivity.class);
            startActivity(loginIntent);
        }
        else{

            setContentView(R.layout.activity_main);

            Button buttonLogIn = findViewById(R.id.buttonLogIn);
            Button buttonSignIn = findViewById(R.id.buttonSignIn);

            buttonLogIn.setOnClickListener(view -> {
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            });

            buttonSignIn.setOnClickListener(view -> {
                Intent intent = new Intent(MainActivity.this,SignInActivity.class);
                startActivity(intent);
            });

        }


    }
}