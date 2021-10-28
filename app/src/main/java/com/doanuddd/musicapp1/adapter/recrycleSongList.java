package com.doanuddd.musicapp1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.doanuddd.musicapp1.R;
import com.doanuddd.musicapp1.model.Song;

import org.w3c.dom.Text;

import java.util.List;

public class recrycleSongList extends RecyclerView.Adapter<recrycleSongList.CustomeViewHolder> {

    private List<Song> songList;

    public recrycleSongList(List<Song> songList) {
        this.songList = songList;
    }

    @NonNull
    @Override
    public recrycleSongList.CustomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_layout, parent, false);
        return new CustomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull recrycleSongList.CustomeViewHolder holder, int position) {

//        holder.txtViewSongName.setText(songList.get(position).getCreatedAt());
//        holder.txtViewSongArtist.setText(songList.get(position).getUpdatedAt());
//        holder.txtViewUrl.setText(songList.get(position).getTitle());
//        holder.txtViewImage.setText(songList.get(position).getBody());

    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    public class CustomeViewHolder extends RecyclerView.ViewHolder{

        TextView txtViewSongName, txtViewSongArtist, txtViewUrl, txtViewImage;

        public CustomeViewHolder(@NonNull View itemView){

            super(itemView);

            txtViewSongName = itemView.findViewById(R.id.title);
            txtViewSongArtist = itemView.findViewById(R.id.artist);
            txtViewUrl = itemView.findViewById(R.id.url);
            txtViewImage = itemView.findViewById(R.id.coverImage);
        }
    }
}
