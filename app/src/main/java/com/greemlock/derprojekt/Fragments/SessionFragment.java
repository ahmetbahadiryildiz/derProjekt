package com.greemlock.derprojekt.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.greemlock.derprojekt.Objects.Message;
import com.greemlock.derprojekt.Objects.Session;
import com.greemlock.derprojekt.Objects.User;
import com.greemlock.derprojekt.R;
import com.greemlock.derprojekt.SessionActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class SessionFragment extends Fragment {

    User currentUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_session, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");
        Query findUser = databaseReference.orderByChild("userUID").equalTo(firebaseUser.getUid());

        findUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot results: snapshot.getChildren()){
                    currentUser = results.getValue(User.class);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("FireData Error:",error.getDetails());
            }
        });



        Button buttonNewSession = getActivity().findViewById(R.id.buttonNewSession);
        String getTime = Calendar.getInstance().getTime().toString();
        ArrayList<Message> messageList = new ArrayList<>();
        buttonNewSession.setOnClickListener(view1 -> {
            Session newSession = new Session("",currentUser.getUserAdvisorUid(),firebaseUser.getUid(),getTime,messageList);

            DatabaseReference databaseReferenceSessions = FirebaseDatabase.getInstance().getReference("sessions");
            databaseReferenceSessions.push().setValue(newSession);

            Query addSessionKey = databaseReferenceSessions.orderByChild("sessionStartDate").equalTo(getTime);
            addSessionKey.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot results: snapshot.getChildren()){

                        String             sessionKey  = results.getKey();
                        Map<String,Object> m_set_key = new HashMap<>();

                        m_set_key.put("sessionKey",sessionKey);
                        databaseReferenceSessions.child(sessionKey).updateChildren(m_set_key);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.e("FireData Error:",error.getDetails());
                }
            });
            Intent IntentStartNewSession = new Intent(getActivity(), SessionActivity.class);
            startActivity(IntentStartNewSession);
        });
    }
}