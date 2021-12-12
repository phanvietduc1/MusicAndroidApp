package com.doanuddd.musicapp1.activity;

import androidx.appcompat.app.AppCompatActivity;
import com.doanuddd.musicapp1.R;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;

public class UserProfileActivity extends AppCompatActivity {

    EditText edname, edemail;
    private SQLiteDatabase db;
    private String name, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        init();
    }

    private void init() {
        edname.setText(name);
        edemail.setText(email);
    }

    public void getData() {
        String sql = "SELECT * FROM user";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToLast();
        if (!cursor.isAfterLast()){
            name = cursor.getString(1);
            email = cursor.getString(2);
            password = cursor.getString(3);
        }
    }
}