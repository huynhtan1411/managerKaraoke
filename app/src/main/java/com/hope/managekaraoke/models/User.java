package com.hope.managekaraoke.models;

public class User {
    private String UserName;
    private String Pass;
    private String FullName;
    private String role;
    private String DayOfBirth;

    //Boolean sex;


    public User() {
    }


    public void setDayOfBirth(String dayOfBirth) {
        this.DayOfBirth = dayOfBirth;
    }
    public String getDayOfBirth() {
        return DayOfBirth;
    }
    public void setUserName(String userName) {
        this.UserName = userName;
    }

    public String getUserName() {
        return UserName;
    }

    public String getPassword() {
        return Pass;
    }

    public void setPassword(String pass) {
        this.Pass = pass;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        this.FullName = fullName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }



    public User(String userName, String pass, String fullName, String role,String dayOfBirth) {
        this.UserName = userName;
        this.Pass = pass;
        this.FullName = fullName;
        this.role = role;
        this.DayOfBirth = dayOfBirth;
    }

    public User(String userName, String fullName, String role,String dayOfBirth) {
        this.UserName = userName;
        this.FullName = fullName;
        this.role = role;
        this.DayOfBirth = dayOfBirth;
    }
}
