package com.doanuddd.musicapp1.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.doanuddd.musicapp1.R;
import com.doanuddd.musicapp1.adapter.MainViewPageAdapter;
import com.doanuddd.musicapp1.fragment.FragmentSetting;
import com.doanuddd.musicapp1.fragment.FragmentLibrary;
import com.doanuddd.musicapp1.fragment.FragmentHome;
import com.doanuddd.musicapp1.fragment.FragmentSearch;
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
        mainViewPageAdapter.addFragment(new FragmentHome(), "Home");
        mainViewPageAdapter.addFragment(new FragmentSearch(), "Search");
        mainViewPageAdapter.addFragment(new FragmentLibrary(), "Library");
        mainViewPageAdapter.addFragment(new FragmentSetting(), "AAA");
        
        viewPager.setAdapter(mainViewPageAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.icontrangchu);
        tabLayout.getTabAt(1).setIcon(R.drawable.icontimkiem);
        tabLayout.getTabAt(2).setIcon(R.drawable.iconthuvien);
        tabLayout.getTabAt(3).setIcon(R.drawable.iconlogo);
    }
}