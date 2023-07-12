package com.greemlock.derprojekt.Objects;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Message {

    private String message_id;
    private String message_receiver_uid;
    private String message_sender_uid;
    private String message;
    private String message_date;

    public Message() {
    }

    public Message(String message_id, String message_receiver_uid, String message_sender_uid, String message, String message_date) {
        this.message_id = message_id;
        this.message_receiver_uid = message_receiver_uid;
        this.message_sender_uid = message_sender_uid;
        this.message = message;
        this.message_date = message_date;
    }

    public String getMessage_id() {
        return message_id;
    }
    public void setMessage_id(String message_id) {
        this.message_id = message_id;
    }

    public String getMessage_receiver_uid() {
        return message_receiver_uid;
    }
    public void setMessage_receiver_uid(String message_receiver_uid) {
        this.message_receiver_uid = message_receiver_uid;
    }

    public String getMessage_sender_uid() {
        return message_sender_uid;
    }
    public void setMessage_sender_uid(String message_sender_uid) {
        this.message_sender_uid = message_sender_uid;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage_date() {
        return message_date;
    }
    public void setMessage_date(String message_date) {
        this.message_date = message_date;
    }

    public void sendMessage(){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("messages");
        databaseReference.push().setValue(this);

        Query addMessageID = databaseReference.orderByChild("message").equalTo(message);
        addMessageID.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Message message = dataSnapshot.getValue(Message.class);
                    if (message.getMessage_id() == ""){
                        String messageKey = dataSnapshot.getKey();
                        Map<String, Object> stringObjectMap = new HashMap<>();
                        stringObjectMap.put("message_id",messageKey);
                        databaseReference.child(messageKey).updateChildren(stringObjectMap);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
