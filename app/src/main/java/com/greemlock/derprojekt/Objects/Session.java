package com.greemlock.derprojekt.Objects;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Session {
    private String sessionKey;
    private String sessionAdvisorUID;
    private String sessionAdviseeUID;
    private String sessionStartDate;
    private ArrayList<Message> sessionMessages;

    public Session(String sessionKey, String sessionAdvisorUID, String sessionAdviseeUID, String sessionStartDate, ArrayList<Message> sessionMessages) {
        this.sessionKey = sessionKey;
        this.sessionAdvisorUID = sessionAdvisorUID;
        this.sessionAdviseeUID = sessionAdviseeUID;
        this.sessionStartDate = sessionStartDate;
        this.sessionMessages = sessionMessages;
    }

    public Session() {
    }

    public String getSessionKey() {
        return sessionKey;
    }
    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getSessionAdvisorUID() {
        return sessionAdvisorUID;
    }
    public void setSessionAdvisorUID(String sessionAdvisorUID) {
        this.sessionAdvisorUID = sessionAdvisorUID;
    }

    public String getSessionAdviseeUID() {
        return sessionAdviseeUID;
    }
    public void setSessionAdviseeUID(String sessionAdviseeUID) {
        this.sessionAdviseeUID = sessionAdviseeUID;
    }

    public String getSessionStartDate() {
        return sessionStartDate;
    }
    public void setSessionStartDate(String sessionStartDate) {
        this.sessionStartDate = sessionStartDate;
    }

    public List<Message> getSessionMessages() {
        return sessionMessages;
    }
    public void setSessionMessages(ArrayList<Message> sessionMessages) {
        this.sessionMessages = sessionMessages;
    }

}
