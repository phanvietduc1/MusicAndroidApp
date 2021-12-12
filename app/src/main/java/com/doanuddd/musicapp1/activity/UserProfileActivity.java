package com.doanuddd.musicapp1.activity;

import androidx.appcompat.app.AppCompatActivity;
import com.doanuddd.musicapp1.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class UserProfileActivity extends AppCompatActivity {

    TextInputLayout edname, edemail;
    TextInputEditText edname2, edemail2;
    private SQLiteDatabase db;
    private String name, email, password;
    ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        db = openOrCreateDatabase("userdb", MODE_PRIVATE, null);
        getData();
        init();
    }

    private void init() {
        edname = findViewById(R.id.edit_name_profile);
        edname2 = findViewById(R.id.edit_name_profile2);
        edname2.setText(name);
        edname.setEnabled(false);

        edemail = findViewById(R.id.edit_email_profile);
        edemail.setEnabled(false);
        edemail2 = findViewById(R.id.edit_email_profile2);
        edemail2.setText(email);
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
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