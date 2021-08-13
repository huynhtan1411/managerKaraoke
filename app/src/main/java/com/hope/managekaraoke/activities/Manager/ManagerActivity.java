package com.hope.managekaraoke.activities.Manager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.hope.managekaraoke.R;

public class ManagerActivity extends AppCompatActivity {
    Button btnManageEmployee, btnManageRoom;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mn_activity_manager);
        mapping();
        clicking();
    }
    private void clicking() {
        btnManageEmployee.setOnClickListener(view -> {
            Intent intent = new Intent(ManagerActivity.this, ManageEmployeeActivity.class);
            startActivity(intent);
        });

        btnManageRoom.setOnClickListener(view -> {
            Intent intent = new Intent(ManagerActivity.this, ManageRoomActivity.class);
            startActivity(intent);
        });
    }

    private void mapping() {
        btnManageRoom = findViewById(R.id.btnManageRoom);
        btnManageEmployee = findViewById(R.id.btnManageEmployee);
    }
}
