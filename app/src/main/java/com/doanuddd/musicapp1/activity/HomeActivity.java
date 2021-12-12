package com.doanuddd.musicapp1.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;

import com.doanuddd.musicapp1.MyPlayService;
import com.doanuddd.musicapp1.R;
import com.doanuddd.musicapp1.adapter.MainViewPageAdapter;
import com.doanuddd.musicapp1.fragment.FragmentHome;
import com.doanuddd.musicapp1.fragment.FragmentLibrary;
import com.doanuddd.musicapp1.fragment.FragmentSearch;
import com.doanuddd.musicapp1.fragment.FragmentSetting;
import com.doanuddd.musicapp1.model.Song;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    Intent serviceIntent, intent;
    Boolean musicPlaying = false;
    public static ArrayList<Song> songArrayList = new ArrayList<>();
    TextView txtName, txtArtist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        anhxa();
        init();
        intent = getIntent();
        serviceIntent = new Intent(this, MyPlayService.class);
        GetDataFromIntent();


    }

    private void anhxa(){
        txtName = findViewById(R.id.txtMiniBarName);
        txtArtist = findViewById(R.id.txtMiniBarArtist);
        tabLayout = findViewById(R.id.myTabLayout);
        viewPager = findViewById(R.id.myViewPager);
        ImageButton btn = (ImageButton)findViewById(R.id.btn_mini_bar);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Cho nay de mo lai man hinh play nhac
                if (musicPlaying){
                    stopService();
                    musicPlaying = false;
                } else {
                    startService();
                    musicPlaying = true;
                }
            }
        });
    }

    private void startService(){
        serviceIntent.putExtra("linkSong", songArrayList.get(0).getLinkBaiHat());
        try {
            startService(serviceIntent);
        } catch (SecurityException e){
            Toast.makeText(HomeActivity.this, "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void stopService(){
        try {
            stopService(serviceIntent);
        } catch (SecurityException e){
            Toast.makeText(HomeActivity.this, "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void GetDataFromIntent() {
        songArrayList.clear();

        if (intent != null) {
            if (intent.hasExtra("song")) {
                Song baiHat = intent.getParcelableExtra("song");
                songArrayList.add(baiHat);
                musicPlaying = true;

                txtName.setText(songArrayList.get(0).getTenBaiHat());
                txtArtist.setText(songArrayList.get(0).getCaSi());
            }
        }
    }

    @SuppressLint("ResourceAsColor")
    private void init(){
        MainViewPageAdapter mainViewPageAdapter = new MainViewPageAdapter(getSupportFragmentManager());
        mainViewPageAdapter.addFragment(new FragmentHome(), null);
        mainViewPageAdapter.addFragment(new FragmentSearch(), null);
        mainViewPageAdapter.addFragment(new FragmentLibrary(), null);
        mainViewPageAdapter.addFragment(new FragmentSetting(), null);
        
        viewPager.setAdapter(mainViewPageAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_search);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_lib);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_setting);
    }
}