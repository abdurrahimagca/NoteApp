package com.example.noteapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Initialize Firebase Auth

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Button loginButton = findViewById(R.id.login);
        Button signupButton = findViewById(R.id.signup);
        EditText mail = findViewById(R.id.email_tf);
        EditText pass = findViewById(R.id.password_tf);
        mAuth = FirebaseAuth.getInstance();
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                //changeInProgress(true);
                firebaseAuth.signInWithEmailAndPassword(mail.getText().toString().trim(),pass.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        startActivity(new Intent(MainActivity.this,MainPage.class));

                    }
                    else{
                        //todo: handle with if task is unsuccessful
                    }
                    }
                });
            }
        });
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,CreateUser.class));
            }
        });




    }
}



