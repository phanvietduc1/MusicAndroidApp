package com.doanuddd.musicapp1.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.doanuddd.musicapp1.R;
import com.doanuddd.musicapp1.adapter.MainViewPageAdapter;
import com.doanuddd.musicapp1.fragment.bxh;
import com.doanuddd.musicapp1.fragment.category;
import com.doanuddd.musicapp1.fragment.home;
import com.doanuddd.musicapp1.fragment.timkiem;
import com.google.android.material.tabs.TabLayout;

public class HomeActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        anhxa();
        init();

    }

    private void anhxa(){
        // hello
        tabLayout = findViewById(R.id.myTabLayout);
        viewPager = findViewById(R.id.myViewPager);
    }

    private void init(){
        MainViewPageAdapter mainViewPageAdapter = new MainViewPageAdapter(getSupportFragmentManager());
        mainViewPageAdapter.addFragment(new home(), "Trang Chủ");
        mainViewPageAdapter.addFragment(new bxh(), "BXH");
        mainViewPageAdapter.addFragment(new category(), "Chủ đề");
        mainViewPageAdapter.addFragment(new timkiem(), "My Music");
        mainViewPageAdapter.addFragment(new timkiem(), "Thông báo");
        viewPager.setAdapter(mainViewPageAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home_black_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_dashboard_black_24dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.chude);
        tabLayout.getTabAt(3).setIcon(R.drawable.mymusic);
        tabLayout.getTabAt(4).setIcon(R.drawable.thongbao);
    }
}