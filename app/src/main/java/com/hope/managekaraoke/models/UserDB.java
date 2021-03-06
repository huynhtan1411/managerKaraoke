package com.hope.managekaraoke.models;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hope.managekaraoke.models.User;

public class UserDB{
    private static DatabaseReference db = FirebaseDatabase.getInstance().getReference();

    public static void addUser(User user, Context context) {
        db.child("Account").child(user.getUserName()).setValue(user, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                if (error != null) {
                    Toast.makeText(context, "Upload failed", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Upload Success", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
