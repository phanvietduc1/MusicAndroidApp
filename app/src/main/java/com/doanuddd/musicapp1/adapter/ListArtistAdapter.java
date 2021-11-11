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
import com.doanuddd.musicapp1.model.Artist;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListArtistAdapter extends RecyclerView.Adapter<ListArtistAdapter.CustomViewHolder> {

    private List<Artist> artistList;
    Context context;

    public ListArtistAdapter(List<Artist> artistList, Context context) {
        this.artistList = artistList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_artist, parent, false);

        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        Picasso.with(context).load(artistList.get(position).getHinhNgheSi()).into(holder.artistImageView);
        holder.artistTextView.setText(artistList.get(position).getTenNgheSi());

        Log.d("aa", holder.artistTextView.getText().toString());
    }

    @Override
    public int getItemCount() {
        System.out.println("So nghe si: " + artistList.size());
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
