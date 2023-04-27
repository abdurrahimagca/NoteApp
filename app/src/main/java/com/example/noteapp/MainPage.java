package com.example.noteapp;

import static com.example.noteapp.R.id.exit_button;
import static com.example.noteapp.R.id.new_button;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainPage extends AppCompatActivity {


    private RecyclerView recyclerView;
    private NoteAdapter notesAdapter;
    private List<Note> notesList;
    private dbHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        Button newNote = findViewById(new_button);
        Button logOut = findViewById(exit_button);
        RecyclerView recyclerView  = findViewById(R.id.recyclerView);
        databaseHelper = new dbHelper(this);
        UserSessionManager userSessionManager = new UserSessionManager(getApplicationContext());

        // Retrieve notes for a specific user ID from the database
       // replace with the desired user ID
        notesList = databaseHelper.getNotesByUserId(userSessionManager.getUserId());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        notesAdapter = new NoteAdapter(this, notesList);
        recyclerView.setAdapter(notesAdapter);



    //todo nota basılı tutulunca silinmeli ya da sil butonu eklenmeli

        newNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainPage.this,NewNote.class));

            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userSessionManager.logout();
                startActivity(new Intent(MainPage.this,MainActivity.class));

            }
        });
    }






}