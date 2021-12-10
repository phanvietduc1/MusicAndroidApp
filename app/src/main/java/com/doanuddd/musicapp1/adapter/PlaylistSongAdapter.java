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

import java.util.ArrayList;

public class PlaylistSongAdapter extends RecyclerView.Adapter<PlaylistSongAdapter.ViewHolder>{

    Context context;
    ArrayList<Song> listSong;

    public PlaylistSongAdapter(Context context, ArrayList<Song> listSong) {
        this.context = context;
        this.listSong = listSong;
    }

    @NonNull
    @Override
    public PlaylistSongAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_playlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaylistSongAdapter.ViewHolder holder, int position) {
        Song baiHat = listSong.get(position);
        holder.txttentimkiem.setText(baiHat.getTenBaiHat());
        holder.txtcasitimkiem.setText(baiHat.getCaSi());
        holder.imganhtimkiem.setImageBitmap(baiHat.getHinhBaiHatBit());
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
            txttentimkiem = itemView.findViewById(R.id.txttennhacplaylist);
            txtcasitimkiem = itemView.findViewById(R.id.txttencasiplaylist);
            imganhtimkiem = itemView.findViewById(R.id.roundedImageView2);

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
