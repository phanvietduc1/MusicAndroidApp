package com.doanuddd.musicapp1.activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.doanuddd.musicapp1.model.User;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "userdb";

    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "user";

    private static final String ID_COL = "id";

    private static final String NAME_COL = "name";

    private static final String EMAIL_COL = "email";

    private static final String PASSWORD_COL = "password";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + EMAIL_COL + " TEXT,"
                + PASSWORD_COL + " TEXT)";
        db.execSQL(query);
    }

    public void addNewUser(String name, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME_COL, name);
        values.put(EMAIL_COL, email);
        values.put(PASSWORD_COL, password);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void clearData(String courseName) {

        // on below line we are creating
        // a variable to write our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are calling a method to delete our
        // course and we are comparing it with our course name.
        db.delete(TABLE_NAME, "name=?", new String[]{courseName});
        db.close();
    }

    public User readUser() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        User a = new User();
        a.setName(cursorCourses.getString(1));
        a.setEmail(cursorCourses.getString(2));
        cursorCourses.close();
        return a;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addNewUser(String name, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME_COL, name);
        values.put(EMAIL_COL, email);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
}
