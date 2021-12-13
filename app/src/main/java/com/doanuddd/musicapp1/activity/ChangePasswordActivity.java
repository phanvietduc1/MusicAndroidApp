package com.doanuddd.musicapp1.activity;

import androidx.appcompat.app.AppCompatActivity;
import com.doanuddd.musicapp1.R;
import com.doanuddd.musicapp1.model.User;
import com.doanuddd.musicapp1.retrofit.ApiClient;
import com.doanuddd.musicapp1.retrofit.UserApi;
import com.google.android.material.textfield.TextInputEditText;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordActivity extends AppCompatActivity {

    ImageButton btnBack;
    Button btnConfirm;
    TextInputEditText txtOldPass, txtNewPass, txtConfirmPass;
    ProgressDialog LoadingBar;
    private SQLiteDatabase db;
    private String name, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        db = openOrCreateDatabase("userdb", MODE_PRIVATE, null);
        getData();
        init();
        LoadingBar = new ProgressDialog(this);
        LoadingBar.setCanceledOnTouchOutside(false);
    }

    private void init(){
        btnBack = findViewById(R.id.btnBack);
        btnConfirm = findViewById(R.id.btnConfirm);
        txtNewPass = findViewById(R.id.newPassword2);
        txtOldPass = findViewById(R.id.oldPassword2);
        txtConfirmPass = findViewById(R.id.confirmPass2);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmSent();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void postPassword(){
        LoadingBar.show();
        LoadingBar.setTitle("Change Password");
        LoadingBar.setMessage("Please wait");

        User u = new User();
        u.setEmail(email);
        u.setOldPassword(txtOldPass.getText().toString());
        u.setPassword(txtNewPass.getText().toString());

        UserApi userApi = ApiClient.self().retrofit.create(UserApi.class);
        Call<User> call = userApi.changePassword(u);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {

                    Toast.makeText(ChangePasswordActivity.this, "Change password success", Toast.LENGTH_SHORT).show();
                    LoadingBar.dismiss();
                }
                else {
                    LoadingBar.dismiss();
                    Toast.makeText(ChangePasswordActivity.this, "Change password fail", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("toang",t.getMessage());
                LoadingBar.dismiss();
                Toast.makeText(ChangePasswordActivity.this, "System Error", Toast.LENGTH_SHORT).show();
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

    private void confirmSent(){
        if (txtConfirmPass.getText().toString().equals(txtNewPass.getText().toString())){
            postPassword();
        } else {
            Toast.makeText(ChangePasswordActivity.this, "Wrong confirm password", Toast.LENGTH_SHORT).show();
        }
    }
}