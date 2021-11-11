package com.doanuddd.musicapp1.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.doanuddd.musicapp1.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//import org.snowcorp.login.helper.DatabaseHandler;
//import org.snowcorp.login.helper.Functions;
//import org.snowcorp.login.helper.SessionManager;


public class LoginActivity extends AppCompatActivity {

    private MaterialButton btnLogin, btnLinkToRegister, btnForgotPass;
    private TextInputLayout inputPhone, inputPassword;
    String phone, password;
    String userPassword;

    ProgressDialog LoadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoadingBar = new ProgressDialog(this);
        inputPhone = findViewById(R.id.edit_email);
        inputPassword = findViewById(R.id.edit_password);
        btnLinkToRegister = findViewById(R.id.button_register);
        btnLogin = findViewById(R.id.button_login);

        LoadingBar.setTitle("Login Account");
        LoadingBar.setMessage("Please wait");
        LoadingBar.setCanceledOnTouchOutside(false);

        init();
    }

    private void LoginAccount(String phone, String password){
        if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(password)){
            Toast.makeText(this, "Fill out", Toast.LENGTH_SHORT).show();
        } else {
            LoadingBar.show();

            final DatabaseReference mRef;
            mRef = FirebaseDatabase.getInstance().getReference();

            mRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.child("Users").child(phone).exists()){
                        userPassword = snapshot.child("Users").child(phone).child("password").getValue().toString();

                        if (password.equals(userPassword)){
                            Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(i);

                            LoadingBar.dismiss();
                        } else {
                            LoadingBar.dismiss();
                            Toast.makeText(LoginActivity.this, "Wrong password", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        LoadingBar.dismiss();
                        Toast.makeText(LoginActivity.this, "Invalid account", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    private void init(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone = inputPhone.getEditText().getText().toString();
                password = inputPassword.getEditText().getText().toString();

                LoginAccount(phone, password);
            }
        });
        btnLinkToRegister.setOnClickListener(view -> {
           Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(i);
          finish();
        });
    }
}