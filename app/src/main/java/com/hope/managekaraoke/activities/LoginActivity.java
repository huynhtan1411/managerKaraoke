package com.hope.managekaraoke.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.hope.managekaraoke.R;
import com.hope.managekaraoke.activities.Kitchen.KitchenActivity;
import com.hope.managekaraoke.activities.Manager.ManagerActivity;
import com.hope.managekaraoke.activities.Staff.StaffActivity;
import com.hope.managekaraoke.models.LoginHistory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    EditText txtUserName;
    EditText txtPass;
    LoginHistory log;
    DatabaseReference myRef;
//    private static LoginActivity mInstance;
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mapping();

        if (loginIfAlreadyLogged()) {
            txtUserName.setText(setUserString());
            txtPass.setText(setPassString());
            btnLoginClicked();
            Log.e("LoginActivity", "entered");
        }
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnLoginClicked();
            }
        });
    }

    private void btnLoginClicked() {
        if (!validate()) {
            return;
        } else {
            String usernameEntered = txtUserName.getText().toString();
            String passwordEntered = txtPass.getText().toString();
            myRef = FirebaseDatabase.getInstance().getReference("Account");

            Query checkUser = myRef.orderByChild("UserName").equalTo(usernameEntered);

            checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    if (snapshot.exists()) {
                        String PassDb = snapshot.child(usernameEntered).child("Pass").getValue(String.class);
                        if (PassDb.equals(passwordEntered)) {

                            String RoleDb = snapshot.child(usernameEntered).child("role").getValue(String.class);
                            Intent intent;
                            Toast.makeText(LoginActivity.this,
                                    "Logged by: " + snapshot.child(usernameEntered).child("FullName").getValue(String.class),
                                    Toast.LENGTH_LONG).show();

                            if (RoleDb.equals("1")) {
                                intent = new Intent(LoginActivity.this, StaffActivity.class);
                                intent.putExtra("UserNameLogged", usernameEntered);
                                intent.putExtra("Role", 1);
                                startActivity(intent);

                            } else if (RoleDb.equals("2")) {
                                intent = new Intent(LoginActivity.this, KitchenActivity.class);
                                intent.putExtra("UserNameLogged", usernameEntered);
                                intent.putExtra("Role", 2);
                                startActivity(intent);

                            } else if (RoleDb.equals("0")) {
                                intent = new Intent(LoginActivity.this, ManagerActivity.class);
                                startActivity(intent);
                                intent.putExtra("UserNameLogged", usernameEntered);
                                intent.putExtra("Role", 0);
                            } else {

                            }
                            saveUser(usernameEntered, passwordEntered);
                            writeLog(usernameEntered);
                        } else {
                            txtPass.setError("Wrong Pass");
                            txtPass.requestFocus();
                        }

                    } else {
                        txtUserName.setError("Not exist??");
                        txtUserName.requestFocus();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }
    }
    private void writeLog(String userName) {

        //DatabaseReference myRef = database.getInstance().getReference("TimeKeeping");
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

        log = new LoginHistory(userName, currentDate);

        myRef = FirebaseDatabase.getInstance().getReference("TimeKeeping");

        myRef.child(log.getUserName()).child(new SimpleDateFormat("MM/yyyy", Locale.getDefault()).format(new Date())).setValue(log);

        Log.e("usernameEntered", log.getUserName());
        Log.e("roleDb", "usernameEntered");
    }


    private void mapping() {
        btnLogin = findViewById(R.id.btnLogin);
        txtPass = findViewById(R.id.txtPass);
        txtUserName = findViewById(R.id.txtUserName);
    }

    private Boolean validate() {
        String pass = txtPass.getText().toString();
        String acc = txtUserName.getText().toString();
        if (pass.isEmpty()) {
            txtPass.setError("Field cannot be empty");
            return false;
        }
        if (acc.isEmpty()) {
            txtUserName.setError("Field cannot be empty");
            return false;
        }
        return true;
    }
//
    private void saveUser(String userName, String password) {
        SharedPreferences prefs;
        SharedPreferences.Editor editor;

        prefs = getSharedPreferences("My app", MODE_PRIVATE);
        editor = prefs.edit();
        editor.putString("userName", userName);
        editor.putString("password", password);
        editor.commit();
        //gi day :v t copy ma :
    }
//
    private boolean loginIfAlreadyLogged() {
        SharedPreferences prefs;
        prefs = getSharedPreferences("My app", MODE_PRIVATE);
        String name = prefs.getString("UserName", "nothing Here");
        String pass = prefs.getString("Pass", "nothing Here");
        if (name.compareTo("nothing here") != 0 && pass.compareTo("nothing here") != 0) {
            return true;
        }
        return false;
    }
//
    private String setUserString() {
        SharedPreferences prefs = getSharedPreferences("My app", MODE_PRIVATE);
        return prefs.getString("userName", " ");
    }

    private String setPassString() {
        SharedPreferences prefs = getSharedPreferences("My app", MODE_PRIVATE);
        return prefs.getString("password", "wrong");
    }
}

