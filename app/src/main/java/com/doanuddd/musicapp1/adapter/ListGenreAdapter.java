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
import com.doanuddd.musicapp1.model.Genre;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListGenreAdapter extends RecyclerView.Adapter<ListGenreAdapter.CustomViewHolder> {

    private List<Genre> genreList;
    Context context;

    public ListGenreAdapter(List<Genre> genreList, Context context) {
        this.genreList = genreList;
        this.context = context;
    }

    @NonNull
    @Override
    public ListGenreAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_genre, parent, false);

        return new ListGenreAdapter.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListGenreAdapter.CustomViewHolder holder, int position) {
        Picasso.with(context).load(genreList.get(position).getHinhChuDe()).into(holder.genreImageView);
        holder.genreTextView.setText(genreList.get(position).getTenChuDe());
        Log.d("genrename", (genreList.get(position).getTenChuDe()));
    }

    @Override
    public int getItemCount() {
        return genreList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView genreImageView;
        TextView genreTextView;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            genreImageView = itemView.findViewById(R.id.genreImg);
            genreTextView = itemView.findViewById(R.id.genreText);
        }
    }
}