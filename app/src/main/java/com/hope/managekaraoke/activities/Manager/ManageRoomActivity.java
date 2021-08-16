package com.hope.managekaraoke.activities.Manager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.hope.managekaraoke.R;

public class ManageRoomActivity extends AppCompatActivity {
    Button btnRoom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mn_activity_manage_room);
        bind();
    }

    private void bind() {
        btnRoom = findViewById(R.id.btnRoom);
        btnRoom.setOnClickListener(view -> {
            Intent intent = new Intent(ManageRoomActivity.this,CreateRoomActivity.class);
            startActivity(intent);
        });
    }
}
