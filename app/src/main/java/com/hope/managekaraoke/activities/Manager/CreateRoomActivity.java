package com.hope.managekaraoke.activities.Manager;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.hope.managekaraoke.R;
import com.hope.managekaraoke.activities.MyAlertDialog;
import com.hope.managekaraoke.models.User;
import com.hope.managekaraoke.models.UserDB;

public class CreateRoomActivity extends AppCompatActivity {
    EditText txtName;
    EditText txtType;
    EditText txtPrice;
    Button btnConfirm, btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mn_activity_create_room);
        bind();
    }
    private void bind() {
        try {
//            txtName = findViewById(R.id.mn_room_txt_cre_name);
//            txtType = findViewById(R.id.mn_room_txt_cre_type);
//            txtPrice = findViewById(R.id.mn_room_txt_cre_price);
//
//
//            btnConfirm = findViewById(R.id.mn_room_btn_cre_confirm);
//            btnBack = findViewById(R.id.mn_room_btn_cre_back);



            btnConfirm.setOnClickListener(view -> {
                btnConfirmClicked();
            });

            btnBack.setOnClickListener(view -> {
                btnBackClicked();
            });
        } catch (Exception e) {
            e.printStackTrace();
            MyAlertDialog.alert(e.getMessage(), this);
        }

    }

    private void btnConfirmClicked() {
//        try {
//
//
//
//
//
//        } catch (Exception e){
//            e.printStackTrace();
//            MyAlertDialog.alert(e.getMessage(), this);
//        }
    }

    private void btnBackClicked() {
        try {
            onBackPressed();
        } catch (Exception e){
            e.printStackTrace();
            MyAlertDialog.alert(e.getMessage(), this);
        }
    }
}
