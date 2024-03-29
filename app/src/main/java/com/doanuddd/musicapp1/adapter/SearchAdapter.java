package com.doanuddd.musicapp1.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.doanuddd.musicapp1.R;
import com.doanuddd.musicapp1.model.Song;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder>{

    Context context;
    ArrayList<Song> listSong;

    public SearchAdapter(Context context, ArrayList<Song> listSong) {
        this.context = context;
        this.listSong = listSong;
    }

    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_search, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, int position) {
        Song baiHat = listSong.get(position);
        holder.txttentimkiem.setText(baiHat.getTenBaiHat());
        holder.txtcasitimkiem.setText(baiHat.getCaSi());
        Picasso.get(/*context*/).load(baiHat.getHinhBaiHat()).into(holder.imganhtimkiem);
    }

    @Override
    public int getItemCount() {
        return listSong.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txttentimkiem, txtcasitimkiem;
        ImageView imganhtimkiem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txttentimkiem = itemView.findViewById(R.id.txttennhac);
            txtcasitimkiem = itemView.findViewById(R.id.txtcasinhac);
            imganhtimkiem = itemView.findViewById(R.id.imgnhac);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("tagmusic", "onClick: click vao bai hat");
//                    Intent intent = new Intent(context, PlayNhacActivity.class);
//                    intent.putExtra("cakhuc", mangbaihat.get(getPosition()));
//                    context.startActivity(intent);
                }
            });

        }
    }
}
