package com.hope.managekaraoke.activities.Manager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hope.managekaraoke.R;
import com.hope.managekaraoke.models.Feature;
import com.hope.managekaraoke.models.User;
import com.hope.managekaraoke.models.ViewCard;

import java.util.ArrayList;
import java.util.List;

public class ManageEmployeeActivity extends AppCompatActivity {
    Button btnCreate;

    RecyclerView featureRecycler;
    DatabaseReference myRef;
    List<User> users;

    static Context context;

    ViewCard adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mn_activity_manage_employee);
        ArrayList list = new ArrayList<>();
        users = new ArrayList<>();

        btnCreate = findViewById(R.id.btnCreateEmployee);
        featureRecycler = findViewById(R.id.list_employee);
        myRef = FirebaseDatabase.getInstance().getReference("Account");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                ArrayList<Feature> listFeartures = new ArrayList<>();

                featureRecycler.setHasFixedSize(true);
                featureRecycler.setLayoutManager(
                        new LinearLayoutManager(ManageEmployeeActivity
                                .this, LinearLayoutManager
                                .VERTICAL, false));

                for (DataSnapshot ds : snapshot.getChildren()) {
                    User data = ds.getValue(User.class);
                    //helpersClasses.add(data);
                    users.add(data);
                    String userDB = data.getFullName();
                    listFeartures.add(new Feature(
                            R.drawable.img_edit
                            , R.drawable.img_del
                            , userDB));
                }
                adapter = new ViewCard(listFeartures, ManageEmployeeActivity.this);
                featureRecycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        btnCreate.setOnClickListener(view -> {
            Intent intent = new Intent(this, CreateEmployeeActivity.class);
            startActivity(intent);
        });
    }
}
