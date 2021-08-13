package com.hope.managekaraoke.models;

public class LoginHistory {
    String UserName;
    String Date;
    public LoginHistory(){
    }

    public LoginHistory(String userName, String date) {
        UserName = userName;
        Date = date;
    }

    public String getUserName() {
        return UserName;
    }

    public String getDate() {
        return Date;
    }

}
