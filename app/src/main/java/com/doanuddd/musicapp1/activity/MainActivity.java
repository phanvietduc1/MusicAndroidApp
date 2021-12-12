package com.doanuddd.musicapp1.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.doanuddd.musicapp1.R;
import com.doanuddd.musicapp1.adapter.MainViewPageAdapter;
import com.doanuddd.musicapp1.model.User;
import com.doanuddd.musicapp1.retrofit.ApiClient;
import com.doanuddd.musicapp1.retrofit.UserApi;
import com.google.android.material.tabs.TabLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    LinearLayout L1, L2;
    TextView app_tag;
    private SQLiteDatabase db;
    private String name = "", email = "", password = "";
    private String count;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhxa();
        initData();
        getData();

        if (name.isEmpty()) {
            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            Thread thread = new Thread()
            {
                @Override
                public void run(){
                    try {
                        sleep(1000); // 5 second
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    finally {
                        startActivity(i);
                        finish();
                    }
                }
            };
            thread.start();
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    LoginAccount(email, password);
                }
            }, 4000);
        }
    }

    private void LoginAccount(String email, String password){

        if (email != "" && password != "") {
            User u = new User();
            u.setEmail(email);
            u.setPassword(password);

            UserApi userApi = ApiClient.self().retrofit.create(UserApi.class);
            Call<User> call = userApi.authenticate2(u);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful()) {
                        User user = response.body();
                        dbHandler = new DBHandler(MainActivity.this);
                        dbHandler.addNewUser(user.getName(), user.getEmail(), user.getPassword());

                        otpConfirm(user);
                    }
                    else {
                    }
                }
                @Override
                public void onFailure(Call<User> call, Throwable t) {
                }
            });
        }
    }

    private void otpConfirm(User user){
        if (user.getIsConfirmed().equals("1")) {
            Intent i = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(i);
            finish();
            Toast.makeText(MainActivity.this, "Login success", Toast.LENGTH_SHORT).show();
        } else {
            Intent i = new Intent(MainActivity.this, OtpActivity.class);
            i.putExtra("otp", user.getOtp());
            i.putExtra("email", user.getEmail());
            startActivity(i);
            finish();
        }
    }

    private void anhxa(){
        L1 = (LinearLayout) findViewById(R.id.l1);
        L2 = (LinearLayout) findViewById(R.id.l2);
        app_tag = (TextView) findViewById(R.id.app_tag);

    }

    private void initData() {
        db = openOrCreateDatabase("userdb", MODE_PRIVATE, null);
        String sql = "CREATE TABLE IF NOT EXISTS user(id INTEGER PRIMARY KEY AUTOINCREMENT, name text, email text, password text)";
        db.execSQL(sql);
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