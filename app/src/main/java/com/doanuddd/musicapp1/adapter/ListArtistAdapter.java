package com.doanuddd.musicapp1.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.doanuddd.musicapp1.R;
import com.doanuddd.musicapp1.activity.PlayingMusicActivity;
import com.doanuddd.musicapp1.activity.PlaylistActivity;
import com.doanuddd.musicapp1.model.Artist;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListArtistAdapter extends RecyclerView.Adapter<ListArtistAdapter.CustomViewHolder> {

    private List<Artist> artistList;
    Context context;
    View view;

    public ListArtistAdapter(List<Artist> artistList, Context context) {
        this.artistList = artistList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        view = inflater.inflate(R.layout.row_artist, parent, false);

        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        Picasso.get(/*context*/).load(artistList.get(holder.getAdapterPosition()).getHinhNgheSi()).into(holder.artistImageView);
        holder.artistTextView.setText(artistList.get(holder.getAdapterPosition()).getTenNgheSi());
        Log.d("artistname", (artistList.get(holder.getAdapterPosition()).getTenNgheSi()));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, PlaylistActivity.class);
                i.putExtra("artist", artistList.get(holder.getAdapterPosition()));
                view.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return artistList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView artistImageView;
        TextView artistTextView;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            artistImageView = itemView.findViewById(R.id.artistImg);
            artistTextView = itemView.findViewById(R.id.artistText);
        }
    }
}
