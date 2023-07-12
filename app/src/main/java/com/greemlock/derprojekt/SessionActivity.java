package com.greemlock.derprojekt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.greemlock.derprojekt.Adapter.MessageRecyclerAdapter;
import com.greemlock.derprojekt.Objects.Message;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class SessionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        Button buttonSendMessage = findViewById(R.id.buttonSendMessage);
        EditText editTextMessage = findViewById(R.id.editTextMessage);

        RecyclerView recyclerViewSession = findViewById(R.id.recyclerViewSession);
        recyclerViewSession.setHasFixedSize(true);
        recyclerViewSession.setLayoutManager(new LinearLayoutManager(this));

        buttonSendMessage.setOnClickListener(view -> {
            String message = editTextMessage.getText().toString();
            if (!message.equals("")){
                Date currentDate = Calendar.getInstance().getTime();
                assert firebaseUser != null;
                Message newMessage = new Message("","" /* Buraya ne gelecek bilmiyorum */, firebaseUser.getUid(),message,currentDate.toString());
                newMessage.sendMessage();
            }else{Toast.makeText(this, "You cannot send blank message...", Toast.LENGTH_SHORT).show(); }

        });

    }

    @Override
    protected void onResume() {

        RecyclerView recyclerView = findViewById(R.id.recyclerViewSession);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("messages");

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Message> messageList = new ArrayList();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                messageList.clear();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Message objectMessage = dataSnapshot.getValue(Message.class);
                    assert objectMessage != null;
                    if (objectMessage.getMessage_receiver_uid().equals("")){
                        messageList.add(objectMessage);
                    }

                }

                Log.e("stat: ",String.valueOf(messageList.size()));
                MessageRecyclerAdapter messageRecyclerAdapter = new MessageRecyclerAdapter(messageList);
                recyclerView.setAdapter(messageRecyclerAdapter);
                if (messageList.size() > 0){
                    recyclerView.scrollToPosition(messageList.size()-1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
        super.onResume();
    }
}