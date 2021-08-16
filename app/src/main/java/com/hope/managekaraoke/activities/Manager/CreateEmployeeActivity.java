package com.hope.managekaraoke.activities.Manager;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.hope.managekaraoke.R;
import com.hope.managekaraoke.models.UserDB;
import com.hope.managekaraoke.activities.MyAlertDialog;
import com.hope.managekaraoke.models.User;

public class CreateEmployeeActivity extends AppCompatActivity {
    EditText txtUserName;
    EditText txtPassword;
    EditText txtConfirmPassword;
    EditText txtRole;
    EditText txtFullName;
    EditText txtDob;
    Button btnConfirm, btnBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mn_activity_create_employee);
        bind();
    }

    private void bind() {
        try {
            txtUserName = findViewById(R.id.txt_cre_username);
            txtPassword = findViewById(R.id.txt_cre_pass);
            txtConfirmPassword = findViewById(R.id.txt_cre_comfirmpass);
            txtRole = findViewById(R.id.txt_cre_role);
            txtFullName = findViewById(R.id.txt_cre_fullname);
            txtDob = findViewById(R.id.txt_cre_dob);

            btnConfirm = findViewById(R.id.btn_cre_confirm);
            btnBack = findViewById(R.id.btn_cre_back);

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
        try {
            checkValid();
            checkRole();

            User user = new User(txtUserName.getText().toString()
                    ,txtPassword.getText().toString()
                    ,txtFullName.getText().toString()
                    ,txtDob.getText().toString(),txtRole.getText().toString());

            UserDB.addUser(user, this);
            onBackPressed();
        } catch (Exception e){
            e.printStackTrace();
            MyAlertDialog.alert(e.getMessage(), this);
        }
    }

    private void btnBackClicked() {
        try {
            onBackPressed();
        } catch (Exception e){
            e.printStackTrace();
            MyAlertDialog.alert(e.getMessage(), this);
        }
    }
    private void checkValid() {
        try {
            if (txtConfirmPassword.getText().toString().isEmpty() ||
                    txtFullName.getText().toString().isEmpty() ||
                    txtPassword.getText().toString().isEmpty() ||
                    txtRole.getText().toString().isEmpty() ||
                    txtUserName.getText().toString().isEmpty()) {
                throw new Exception("Please provide all the information");
            }
        } catch (Exception e) {
            e.printStackTrace();
            MyAlertDialog.alert(e.getMessage(), this);
        }
    }

    private void checkRole() throws  Exception{
        if (txtRole.getText().toString().compareTo("0") != 0 && txtRole.getText().toString().compareTo("1") != 0 && txtRole.getText().toString().compareTo("2") != 0) {
            throw new Exception("Role can only be 0, 1 or 2!");
        }
    }
}
