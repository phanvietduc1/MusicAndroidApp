package com.doanuddd.musicapp1.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.doanuddd.musicapp1.R;
import com.doanuddd.musicapp1.model.Keyword;
import com.doanuddd.musicapp1.model.User;
import com.doanuddd.musicapp1.retrofit.ApiClient;
import com.doanuddd.musicapp1.retrofit.UserApi;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPasswordActivity extends AppCompatActivity {

    private Button btnSend;
    private TextInputEditText emailToSend;
    private MaterialButton btnLogin;

    String email;
    ProgressDialog LoadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        btnSend = findViewById(R.id.btn_send);
        emailToSend = findViewById(R.id.emailToSend);
        btnLogin = findViewById(R.id.button_login);

        LoadingBar = new ProgressDialog(this);
        LoadingBar.setTitle("Confirm Otp");
        LoadingBar.setMessage("Please wait");
        LoadingBar.setCanceledOnTouchOutside(false);

        init();
    }

    private void init() {
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = emailToSend.getText().toString();
                postEmail(email);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ForgetPasswordActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void postEmail(String email){
        LoadingBar.show();

        Keyword k = new Keyword();
        k.setKeyword(email);

        UserApi userApi = ApiClient.self().retrofit.create(UserApi.class);
        Call<User> call = userApi.resetPassword(k);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(ForgetPasswordActivity.this, "New password has been sent to your email", Toast.LENGTH_SHORT).show();
                    LoadingBar.dismiss();
                }
                else {
                    LoadingBar.dismiss();
                    Toast.makeText(ForgetPasswordActivity.this, "Respone fail", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("toang",t.getMessage());
                LoadingBar.dismiss();
                Toast.makeText(ForgetPasswordActivity.this, "System Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
