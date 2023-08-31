package com.greemlock.derprojekt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.greemlock.derprojekt.Objects.User;

import java.util.Objects;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(view -> finish());

        EditText editTextName = findViewById(R.id.editTextName);
        EditText editTextUsername = findViewById(R.id.editTextUsername);
        EditText editTextEmail = findViewById(R.id.editTextEmail);
        EditText editTextPassword = findViewById(R.id.editTextPassword);

        Button buttonSignIn = findViewById(R.id.buttonSignIn);

        buttonSignIn.setOnClickListener(view -> {


            try {
                String name = editTextName.getText().toString();
                String username = editTextUsername.getText().toString();
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

                firebaseAuth.createUserWithEmailAndPassword(email,password)
                        .addOnSuccessListener(authResult -> {
                            UserProfileChangeRequest request;

                            request = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(name)
                                    .build();

                            FirebaseUser firebaseUser = authResult.getUser();
                            firebaseUser.updateProfile(request);
                        })
                        .addOnCompleteListener(SignInActivity.this, task -> {
                            if (task.isSuccessful()){
                                FirebaseUser user = firebaseAuth.getCurrentUser();
                                assert user != null;
                                user.sendEmailVerification();

                                User newUser = new User(user.getUid(),name,username,email,"");
                                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");
                                databaseReference.push().setValue(newUser);

                                editTextEmail.setText("");
                                editTextName.setText("");
                                editTextPassword.setText("");
                                editTextUsername.setText("");

                                Toast.makeText(SignInActivity.this, "The verification link is sent to your email!", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                            else{
                                Toast.makeText(SignInActivity.this, Objects.requireNonNull(task.getException()).getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }


            catch (Exception e) {
                Log.e("error",e.getLocalizedMessage());
            }

        });


    }
}