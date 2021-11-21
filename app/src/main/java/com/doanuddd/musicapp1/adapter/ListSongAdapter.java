package com.doanuddd.musicapp1.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.doanuddd.musicapp1.R;
import com.doanuddd.musicapp1.model.Song;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListSongAdapter extends RecyclerView.Adapter<ListSongAdapter.CustomViewHolder> {

    private List<Song> songList;
    Context context;
    View view;

    public ListSongAdapter(List<Song> songList, Context context) {
        this.songList = songList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.row_song, parent, false);

        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        Picasso.get(/*context*/).load(songList.get(position).getHinhBaiHat()).into(holder.songImageView);
        holder.songTextView.setText(songList.get(position).getTenBaiHat());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("aa", holder.songTextView.getText().toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView songImageView;
        TextView songTextView;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            songImageView = itemView.findViewById(R.id.songImg);
            songTextView = itemView.findViewById(R.id.songText);
        }
    }
}