package com.example.mixologymania;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class help_screen extends AppCompatActivity {
    EditText enterName, enterEmail, enterPhone, enterMessage;
    Button buttonSubmit;
    FirebaseDatabase mDatabase;
    private DatabaseReference helpRef;

    help_message helpMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_screen);

        enterName = findViewById(R.id.enter_name);
        enterEmail = findViewById(R.id.enter_email);
        enterPhone = findViewById(R.id.enter_phone);
        enterMessage = findViewById(R.id.enter_message);

        mDatabase = FirebaseDatabase.getInstance();

        helpRef = mDatabase.getReference("help");

        buttonSubmit = findViewById(R.id.submit_btn);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = enterName.getText().toString();
                String email = enterEmail.getText().toString();
                String phone = enterPhone.getText().toString();
                String message = enterMessage.getText().toString();

                if (TextUtils.isEmpty(name) && TextUtils.isEmpty(email) && TextUtils.isEmpty(phone) && TextUtils.isEmpty(message)) {
                    Toast.makeText(help_screen.this, "Please add all data.", Toast.LENGTH_SHORT).show();
                } else {
                    addDatatoFirebase(name, email, phone, message);
                }

            }
        });

    }

    private void addDatatoFirebase(String name, String email, String phone, String message) {
        int random_int = (int)Math.floor(Math.random()*(5000)+1);
        helpRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //helpRef.child(""+random_int).setValue(helpMessage);
                helpRef.child(""+random_int).child("userName").setValue(name);
                helpRef.child(""+random_int).child("userEmail").setValue(email);
                helpRef.child(""+random_int).child("phoneNum").setValue(phone);
                helpRef.child(""+random_int).child("userMessage").setValue(message);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(help_screen.this, "Fail to submit " + error, Toast.LENGTH_SHORT).show();
            }
        });
        Toast.makeText(help_screen.this, "Submitted!", Toast.LENGTH_SHORT).show();

    }

}
