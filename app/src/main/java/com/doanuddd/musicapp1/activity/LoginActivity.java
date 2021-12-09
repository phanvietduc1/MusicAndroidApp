package com.doanuddd.musicapp1.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.doanuddd.musicapp1.R;
import com.doanuddd.musicapp1.model.User;
import com.doanuddd.musicapp1.retrofit.ApiClient;
import com.doanuddd.musicapp1.retrofit.UserApi;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//import org.snowcorp.login.helper.DatabaseHandler;
//import org.snowcorp.login.helper.Functions;
//import org.snowcorp.login.helper.SessionManager;


public class LoginActivity extends AppCompatActivity {

    private MaterialButton btnLogin, btnLinkToRegister, btnForgotPass;
    private TextInputLayout inputPhone, inputPassword;
    String email, password;
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

    private void LoginAccount(String email, String password){
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
            Toast.makeText(this, "Fill out", Toast.LENGTH_SHORT).show();
        } else {
            LoadingBar.show();
            if (email != "" && password != "") {
                User u = new User();
                u.setEmail(email);
                u.setPassword(password);

                UserApi userApi = ApiClient.self().retrofit.create(UserApi.class);
                Call<User> call = userApi.authenticate(u);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()) {
                            User user = response.body();

                            otpConfirm(user);

                            LoadingBar.dismiss();
                        }
                        else {
                            LoadingBar.dismiss();
                            Toast.makeText(LoginActivity.this, "Wrong email or password", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.e("toang",t.getMessage());
                        LoadingBar.dismiss();
                        Toast.makeText(LoginActivity.this, "Error Login2", Toast.LENGTH_SHORT).show();
                    }
                });
            }

        }
    }

    private void init(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = inputPhone.getEditText().getText().toString();
                password = inputPassword.getEditText().getText().toString();

                LoginAccount(email, password);
            }
        });
        btnLinkToRegister.setOnClickListener(view -> {
           Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(i);
          finish();
        });
    }

    private void otpConfirm(User user){
        if (user.getIsConfirmed() == "1") {
            Intent i = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(i);
            Toast.makeText(LoginActivity.this, "Login success", Toast.LENGTH_SHORT).show();
        } else {
            Intent i = new Intent(LoginActivity.this, OtpActivity.class);
            i.putExtra("otp", user.getOtp());
            i.putExtra("email", user.getEmail());
            startActivity(i);
        }
    }
}