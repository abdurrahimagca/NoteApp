package com.example.noteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class dbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "myDatabase.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_USERS = "users";
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "pass";

    private static final String TABLE_NOTES = "notes";
    private static final String COLUMN_NOTE_ID = "note_id";
    private static final String COLUMN_NOTE_HEAD = "note_head";
    private static final String COLUMN_NOTE_BODY = "note_body";
    private static final String COLUMN_USER_ID_FK = "user_id";

    public dbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory,int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createUsersTableQuery = "CREATE TABLE " + TABLE_USERS + "("
                + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_USERNAME + " TEXT NOT NULL, "
                + COLUMN_PASSWORD + " TEXT NOT NULL"
                + ")";
        sqLiteDatabase.execSQL(createUsersTableQuery);

        // Create notes table
        String createNotesTableQuery = "CREATE TABLE " + TABLE_NOTES + "("
                + COLUMN_NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NOTE_HEAD + " TEXT NOT NULL, "
                + COLUMN_NOTE_BODY + " TEXT NOT NULL, "
                + COLUMN_USER_ID_FK + " INTEGER, "
                + "FOREIGN KEY (" + COLUMN_USER_ID_FK + ") REFERENCES " + TABLE_USERS + "(" + COLUMN_USER_ID + ")"
                + ")";
        sqLiteDatabase.execSQL(createNotesTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(sqLiteDatabase);

    }
    public void createUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, password);



        // Inserting row
        db.insert(TABLE_USERS, COLUMN_USERNAME, values);
        System.out.println(getCurrentUserId(username));
        db.close(); // Closing database connection
    }
    public boolean isUserValid(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();


        String[] projection = {COLUMN_USER_ID};
        String selection = COLUMN_USERNAME + " = ? AND " + COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {username, password};

        Cursor cursor = db.query(TABLE_USERS, projection, selection, selectionArgs, null, null, null);
        boolean isValid = cursor.getCount() > 0;

        cursor.close();
        db.close();

        return isValid;
    }
    public List<String[]> getNotesByUserId(int userId) {
        List<String[]> notesList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {COLUMN_NOTE_HEAD, COLUMN_NOTE_BODY};
        String selection = COLUMN_USER_ID_FK + " = ?";
        String[] selectionArgs = {String.valueOf(userId)};

        Cursor cursor = db.query(TABLE_NOTES, projection, selection, selectionArgs, null, null, null);

        // Loop through all rows and add to list
        if (cursor.moveToFirst()) {
            do {
                String[] note = new String[2];
                note[0] = cursor.getString(0); // Note Head
                note[1] = cursor.getString(1); // Note Body
                notesList.add(note);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return notesList;
    }
    public void addNote(String noteHead, String noteBody, int userId) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NOTE_HEAD, noteHead);
        values.put(COLUMN_NOTE_BODY, noteBody);
        values.put(COLUMN_USER_ID_FK, userId);

        // Inserting row
        db.insert(TABLE_NOTES, null, values);
        db.close(); // Closing database connection
    }
    public int getCurrentUserId(String username) {
        int userId = -1; // Default value if user not found
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {COLUMN_USER_ID};
        String selection = COLUMN_USERNAME + " = ?";
        String[] selectionArgs = {username};

        Cursor cursor = db.query(TABLE_USERS, projection, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            userId = cursor.getInt(0); // Get the user ID
        }

        cursor.close();
        db.close();

        return userId;
    }





}
