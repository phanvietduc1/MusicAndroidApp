package com.doanuddd.musicapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    LinearLayout L1, L2;
    TextView app_tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        L1 = (LinearLayout) findViewById(R.id.l1);
        L2 = (LinearLayout) findViewById(R.id.l2);

        app_tag = (TextView) findViewById(R.id.app_tag);

        Intent i = new Intent(MainActivity.this, HomeActivity.class);
        Thread thread = new Thread()
        {
            @Override
            public void run(){
                try {
                    sleep(5000); // 5 second
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
    }
}