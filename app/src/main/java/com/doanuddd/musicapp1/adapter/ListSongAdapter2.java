package com.doanuddd.musicapp1.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.doanuddd.musicapp1.R;
import com.doanuddd.musicapp1.model.Song;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListSongAdapter2 extends PagerAdapter {
    Context context;
    ArrayList<Song> arraySongList;

    public ListSongAdapter2(Context context, ArrayList<Song> arraySongList) {
        this.context = context;
        this.arraySongList = arraySongList;
    }

    @Override
    public int getCount() {
        return arraySongList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_song, null);

        ImageView songImageView = view.findViewById(R.id.songImg);
        TextView songTextView = view.findViewById(R.id.songText);

        Picasso.with(context).load(arraySongList.get(position).getHinhBaiHat()).into(songImageView);
        songTextView.setText(arraySongList.get(position).getTenBaiHat());


        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
