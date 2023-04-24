package com.example.noteapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class CreateUser extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        Button submitButton;
        submitButton = findViewById(R.id.submit_button);
        EditText mail = findViewById(R.id.email_address);
        EditText pass = findViewById(R.id.password);
        submitButton.setOnClickListener(new View.OnClickListener() {
            //SQLiteDatabase DbHelper = new dbHelper(getApplicationContext(), null, null, 1);
            @Override
            public void onClick(View view) {
                //todo: create a new account
                dbHelper d = new dbHelper(getApplicationContext(), null,null,1);
                d.createUser(mail.getText().toString(), pass.getText().toString());
                startActivity(new Intent(CreateUser.this, MainActivity.class));
            }
        });
    }
}