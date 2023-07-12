package com.greemlock.derprojekt.Objects;

public class User {
    String userUID;
    String userName;
    String userUsername;
    String userEmail;
    String userAdvisorUid;

    public User() {
    }

    public User(String userUID, String userName, String userUsername, String userEmail, String userAdvisorUid) {
        this.userUID = userUID;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userUsername = userUsername;
        this.userAdvisorUid = userAdvisorUid;
    }

    public String getUserUID() {
        return userUID;
    }
    public void setUserUID(String userUID) {
        this.userUID = userUID;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserUsername() {
        return userUsername;
    }
    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }

    public String getUserEmail() {
        return userEmail;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserAdvisorUid() {
        return userAdvisorUid;
    }
    public void setUserAdvisorUid(String userAdvisorUid) {
        this.userAdvisorUid = userAdvisorUid;
    }
}
