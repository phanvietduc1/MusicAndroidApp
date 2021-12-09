package com.doanuddd.musicapp1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.doanuddd.musicapp1.R;
import com.doanuddd.musicapp1.model.Song;

public class OtpActivity extends AppCompatActivity {

    public EditText otp1;
    public EditText otp2;
    public EditText otp3;
    public EditText otp4;
    TextView sendToEmail;

    String otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        otp1 = (EditText) findViewById(R.id.et_1);
        otp2 = (EditText) findViewById(R.id.et_2);
        otp3 = (EditText) findViewById(R.id.et_3);
        otp4 = (EditText) findViewById(R.id.et_4);

        sendToEmail = findViewById(R.id.sendToEmail);

        init();

        Intent intent = getIntent();

        if (intent != null) {
            if (intent.hasExtra("otp")) {
                otp = intent.getStringExtra("otp");
            }
            if (intent.hasExtra("email")) {
                sendToEmail.setText("Please type the verification code sent\nto " + intent.getStringExtra("email"));
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
                        Intent i = new Intent(OtpActivity.this, HomeActivity.class);
                        startActivity(i);
                        Toast.makeText(OtpActivity.this, "Confirm Otp", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(OtpActivity.this, "Wrong Otp", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
