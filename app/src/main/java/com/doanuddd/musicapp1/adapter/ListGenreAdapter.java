package com.doanuddd.musicapp1.adapter;


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
import com.doanuddd.musicapp1.activity.DanhsachbaihatActivity;
import com.doanuddd.musicapp1.model.Genre;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

public class ListGenreAdapter extends RecyclerView.Adapter<ListGenreAdapter.CustomViewHolder> {

    private List<Genre> genreList;
    Context context;
    View view;

    public ListGenreAdapter(List<Genre> genreList, Context context) {
        this.genreList = genreList;
        this.context = context;
    }

    @NonNull
    @Override
    public ListGenreAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        view = inflater.inflate(R.layout.row_genre, parent, false);

        return new ListGenreAdapter.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListGenreAdapter.CustomViewHolder holder, int position) {
        Picasso.get(/*context*/).load(genreList.get(position).getHinhChuDe()).into(holder.genreImageView);
        holder.genreTextView.setText(genreList.get(position).getTenChuDe());
        Log.d("genrename", (genreList.get(position).getTenChuDe()));
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DanhsachbaihatActivity.class);
                context.startActivity(intent);
            }
        });
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