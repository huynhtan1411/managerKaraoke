package com.hope.managekaraoke.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hope.managekaraoke.R;
import com.hope.managekaraoke.models.User;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Button btnGo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getInstance().getReference("TimeKeeping");

        // Write a message to the database
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("Account");
//
//        User user = new User("Tan","123","Huynh Dai Tan","1","14/11/2000");
//        myRef.child(user.UserName).setValue(user);
//
//        user = new User("admin","123","Admin","0","07/08/2021");
//        myRef.child(user.UserName).setValue(user);
//        user = new User("Ngan", "123", "Vong Ngoc Thuy Ngan", "2", "1/1/2000");
//        myRef.child(user.UserName).setValue(user);
//        Map<String,String> map = new HashMap<String,String>();
//        myRef = database.getReference("Role");
//        map.put("0","Manager");
//        map.put("1","Staff");
//        map.put("2","Kitchen");
//        myRef.setValue(map);
        btnGo = findViewById(R.id.btnGo);
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(it);
                //TODO:Put some extra
            }
        });







    }
}