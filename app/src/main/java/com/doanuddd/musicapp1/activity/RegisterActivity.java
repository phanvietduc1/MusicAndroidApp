package com.doanuddd.musicapp1.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.doanuddd.musicapp1.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//import org.snowcorp.login.helper.Functions;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    private MaterialButton btnLinkToLogin;
    private Button btnRegister;
    private TextInputLayout inputName, inputPhone, inputPassword;
    String phone, name, password;
    ProgressDialog LoadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        inputPhone = findViewById(R.id.edit_email);
        inputName = findViewById(R.id.edit_name);
        inputPassword = findViewById(R.id.edit_password);
        btnLinkToLogin = findViewById(R.id.button_login);

        btnRegister = (Button)findViewById(R.id.button_register);

        LoadingBar = new ProgressDialog(this);

        LoadingBar.setTitle("Creating Account");
        LoadingBar.setMessage("Waiting");
        LoadingBar.setCanceledOnTouchOutside(false);

        init();
    }

    private void init(){
        btnRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                phone = inputPhone.getEditText().getText().toString();
                password = inputPassword.getEditText().getText().toString();
                name = inputName.getEditText().getText().toString();

                CreateNewAccount(phone, password, name);
            }
        });

        btnLinkToLogin.setOnClickListener(view -> {
            Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
        });
    }

    private void CreateNewAccount(String phone, String password, String name) {
        // check account database empty or not

        if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(password) || TextUtils.isEmpty(name)){
            Toast.makeText(this, "Fill out", Toast.LENGTH_SHORT).show();
        } else {
            LoadingBar.show();

            final DatabaseReference mRef;
            mRef = FirebaseDatabase.getInstance().getReference();

            mRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (!snapshot.child("Users").child(phone).exists()){
                        // no exist account -> create account
                        HashMap<String, Object> userdata = new HashMap<>();

                        userdata.put("phone", phone);
                        userdata.put("name", name);
                        userdata.put("password", password);

                        mRef.child("Users").child(phone).updateChildren(userdata)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()){
                                            LoadingBar.dismiss();
                                            Toast.makeText(RegisterActivity.this, "Register Successfull", Toast.LENGTH_SHORT).show();
                                        } else {
                                            LoadingBar.dismiss();
                                            Toast.makeText(RegisterActivity.this, "Register Fail", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                    } else {
                        LoadingBar.dismiss();
                        Toast.makeText(RegisterActivity.this, "User with this email already exist", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }
}