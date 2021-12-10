package com.doanuddd.musicapp1.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.doanuddd.musicapp1.R;
import com.doanuddd.musicapp1.model.User;
import com.doanuddd.musicapp1.retrofit.ApiClient;
import com.doanuddd.musicapp1.retrofit.UserApi;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

//import org.snowcorp.login.helper.Functions;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private MaterialButton btnLinkToLogin;
    private Button btnRegister;
    private TextInputLayout inputName, inputPhone, inputPassword;
    String phone, name, password;
    ProgressDialog LoadingBar;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

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

    private int Validate(String email, String password, String name){
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(name)){
            Toast.makeText(this, "Fill out", Toast.LENGTH_SHORT).show();
            return 0;
        }

        if (email.trim().matches(emailPattern)) {
        } else {
            Toast.makeText(getApplicationContext(),"Invalid email address", Toast.LENGTH_SHORT).show();
            return 0;
        }

        if (password.length()<6){
            Toast.makeText(getApplicationContext(),"Password must be longer than 6 characters",Toast.LENGTH_SHORT).show();
            return 0;
        }

        return 1;
    }


    private void CreateNewAccount(String email, String password, String name) {
        if (Validate(email,password,name)==1) {
            LoadingBar.show();

            User u = new User();
            u.setName(name);
            u.setEmail(email);
            u.setPassword(password);

            UserApi userApi = ApiClient.self().retrofit.create(UserApi.class);
            Call<User> call = userApi.register(u);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, "Sign Up success", Toast.LENGTH_SHORT).show();
                        LoadingBar.dismiss();
                        Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(i);
                    }
                    else {
                        LoadingBar.dismiss();
                        Toast.makeText(RegisterActivity.this, "Sign Up error 1", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Log.e("toang",t.getMessage());
                    LoadingBar.dismiss();
                    Toast.makeText(RegisterActivity.this, "Sign Up error 2", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}