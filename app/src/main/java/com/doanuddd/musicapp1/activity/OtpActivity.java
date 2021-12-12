package com.doanuddd.musicapp1.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.doanuddd.musicapp1.R;
import com.doanuddd.musicapp1.model.Song;
import com.doanuddd.musicapp1.model.User;
import com.doanuddd.musicapp1.retrofit.ApiClient;
import com.doanuddd.musicapp1.retrofit.UserApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtpActivity extends AppCompatActivity {

    public EditText otp1;
    public EditText otp2;
    public EditText otp3;
    public EditText otp4;
    TextView sendToEmail;

    Button btnConfirm, btnResend;
    ImageButton btnBack;

    String otp;
    String email;

    ProgressDialog LoadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        otp1 = (EditText) findViewById(R.id.et_1);
        otp2 = (EditText) findViewById(R.id.et_2);
        otp3 = (EditText) findViewById(R.id.et_3);
        otp4 = (EditText) findViewById(R.id.et_4);

        sendToEmail = findViewById(R.id.sendToEmail);
        btnResend = findViewById(R.id.btnResend);
        btnConfirm = findViewById(R.id.btnConfirm);
        btnBack = findViewById(R.id.btnBack);

        LoadingBar = new ProgressDialog(this);
        LoadingBar.setCanceledOnTouchOutside(false);

        init();

        Intent intent = getIntent();

        if (intent != null) {
            if (intent.hasExtra("otp")) {
                otp = intent.getStringExtra("otp");
            }
            if (intent.hasExtra("email")) {
                email = intent.getStringExtra("email");
                sendToEmail.setText("Please type the verification code sent\nto " + email);
            }
        }
    }

    private void init(){
        otp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (otp1.getText().toString().length() == 1) {
                    otp2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        otp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (otp2.getText().toString().length() == 1) {
                    otp3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        otp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (otp3.getText().toString().length() == 1) {
                    otp4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        otp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (otp4.getText().toString().length() == 1) {
                    String localOtp = otp1.getText().toString()  + otp2.getText().toString() + otp3.getText().toString() + otp4.getText().toString();
                    if (localOtp.equals(otp)) {
                        postOtp();
                    } else {
                        Toast.makeText(OtpActivity.this, "Wrong otp", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postOtp();
            }
        });

        btnResend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resendOTP();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void postOtp(){
        LoadingBar.show();
        LoadingBar.setTitle("Confirm Otp");
        LoadingBar.setMessage("Please wait");

        User u = new User();
        u.setEmail(email);
        u.setOtp(otp);

        UserApi userApi = ApiClient.self().retrofit.create(UserApi.class);
        Call<User> call = userApi.confirmOtp(u);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    Intent i = new Intent(OtpActivity.this, HomeActivity.class);
                    startActivity(i);
                    Toast.makeText(OtpActivity.this, "Confirm Otp", Toast.LENGTH_SHORT).show();
                    finish();
                    LoadingBar.dismiss();
                }
                else {
                    LoadingBar.dismiss();
                    Toast.makeText(OtpActivity.this, "Respone fail", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("toang",t.getMessage());
                LoadingBar.dismiss();
                Toast.makeText(OtpActivity.this, "System Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void resendOTP(){
        LoadingBar.show();
        LoadingBar.setTitle("Resend Otp");
        LoadingBar.setMessage("Please wait");

        User u = new User();
        u.setEmail(email);

        UserApi userApi = ApiClient.self().retrofit.create(UserApi.class);
        Call<User> call = userApi.resendOTP(u);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    Intent i = new Intent(OtpActivity.this, LoginActivity.class);
                    startActivity(i);
                    Toast.makeText(OtpActivity.this, "Already resend OTP", Toast.LENGTH_SHORT).show();
                    finish();
                    LoadingBar.dismiss();
                }
                else {
                    LoadingBar.dismiss();
                    Toast.makeText(OtpActivity.this, "Respone fail", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("toang",t.getMessage());
                LoadingBar.dismiss();
                Toast.makeText(OtpActivity.this, "System Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
