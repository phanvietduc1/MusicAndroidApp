package com.doanuddd.musicapp1.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.doanuddd.musicapp1.R;
import com.doanuddd.musicapp1.adapter.MainViewPageAdapter;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout L1, L2;
    TextView app_tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhxa();
        init();

        Intent i = new Intent(MainActivity.this, HomeActivity.class);
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
    }

    private void anhxa(){
        L1 = (LinearLayout) findViewById(R.id.l1);
        L2 = (LinearLayout) findViewById(R.id.l2);
        app_tag = (TextView) findViewById(R.id.app_tag);
    }

    private void init(){

    }
}