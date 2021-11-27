package com.doanuddd.musicapp1.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class ViewPagerDisc extends FragmentPagerAdapter {
    public final ArrayList<Fragment> fragments = new ArrayList<>();

    public ViewPagerDisc(@NonNull FragmentManager fm) {
        super(fm);
    }

    public ViewPagerDisc(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
    public void AddFragment(Fragment fragment){
        fragments.add(fragment);
    }
}
