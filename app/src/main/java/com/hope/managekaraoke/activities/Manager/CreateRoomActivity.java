package com.hope.managekaraoke.activities.Manager;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hope.managekaraoke.R;
import com.hope.managekaraoke.activities.MyAlertDialog;
import com.hope.managekaraoke.models.KaraokeRoom;
import com.hope.managekaraoke.models.User;
import com.hope.managekaraoke.models.UserDB;

import java.util.ArrayList;

public class CreateRoomActivity extends AppCompatActivity {
    EditText txtName;
    Spinner spType;
    EditText txtPrice;
    Button btnAdd, btnDel,btnFind;
    DatabaseReference myRef;
    String RoomTypeList[]={"Normal","Vip"};
    String status = "0";
    //ArrayList<String> RoomTypeList = new ArrayList<String>() ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mn_activity_create_room);
        bind();
    }
    private void bind() {
            txtName = findViewById(R.id.mn_room_txt_cre_name);
            spType = findViewById(R.id.mn_room_sp_cre_type);
            txtPrice = findViewById(R.id.mn_room_txt_cre_price);
            btnAdd = findViewById(R.id.mn_room_btn_cre_confirm);
            btnFind = findViewById(R.id.mn_room_btn_cre_find);
            btnDel = findViewById(R.id.mn_room_btn_cre_del);
            btnAdd.setOnClickListener(view -> {
                btnAddClicked();
            });
            btnDel.setOnClickListener(view -> {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("KaraokeRoom");
                myRef.child(spType.getSelectedItem().toString()).child(txtName.getText().toString()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(CreateRoomActivity.this, "Add succeed!", Toast.LENGTH_LONG).show();
                    }
                });
            });
            btnFind.setOnClickListener(view -> {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("KaraokeRoom");
                if (txtName.getText().toString().isEmpty()) {
                    Toast.makeText(CreateRoomActivity.this, "please fill the name field and choose the correct room type!", Toast.LENGTH_LONG).show();
                } else {
                    myRef.child(spType.getSelectedItem().toString()).child(txtName.getText().toString()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists() || snapshot.getValue() != null) {
                            KaraokeRoom k = snapshot.getValue(KaraokeRoom.class);
                            txtPrice.setText(k.getPrice() + "");

                        } else {
                            Toast.makeText(CreateRoomActivity.this, "Karaoke name doesn't exist!", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }

        });

       loadProductTypeList();
        spType = findViewById(R.id.mn_room_sp_cre_type);
    }

    private void btnAddClicked() {
        myRef = FirebaseDatabase.getInstance().getReference("KaraokeRoom");
        KaraokeRoom karaokeRoom = new KaraokeRoom(txtName.getText().toString(),Long.parseLong(txtPrice.getText().toString()),status.toString(),spType.getSelectedItem().toString());

        myRef.child(karaokeRoom.getTypeRoom()).child(karaokeRoom.getNameRoom()).setValue(karaokeRoom).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(CreateRoomActivity.this, "Add succeed!", Toast.LENGTH_LONG).show();
            }
        });
    }
    private void loadProductTypeList() {

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(CreateRoomActivity.this, android.R.layout.simple_spinner_item, RoomTypeList);
        //hien thi ds cho spiner
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spType.setAdapter(adapter);
    }
}

