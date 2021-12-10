package com.doanuddd.musicapp1.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

//import com.example.btl_music4b.Adapter.DanhsachbaihatAdapter;
//import com.example.btl_music4b.Adapter.dsbhthuvienplaylistAdapter;
//import com.example.btl_music4b.Model.BaiHatModel;
//import com.example.btl_music4b.Model.BaiHatThuVienPlayListModel;
//import com.example.btl_music4b.Model.BangXepHangModel;
//import com.example.btl_music4b.Model.ChuDeModel;
//import com.example.btl_music4b.Model.NgheSiModel;
//import com.example.btl_music4b.Model.PhoBienModel;
//import com.example.btl_music4b.Model.PlaylistModel;
//import com.example.btl_music4b.Model.ThinhHanhModel;
//import com.example.btl_music4b.Model.ThuVienPlayListModel;
//import com.example.btl_music4b.R;
//import com.example.btl_music4b.Service.APIService;
//import com.example.btl_music4b.Service.Dataservice;
import com.doanuddd.musicapp1.R;
import com.doanuddd.musicapp1.adapter.PlaylistSongAdapter;
import com.doanuddd.musicapp1.model.Artist;
import com.doanuddd.musicapp1.model.Keyword;
import com.doanuddd.musicapp1.model.Song;
import com.doanuddd.musicapp1.retrofit.ApiClient;
import com.doanuddd.musicapp1.retrofit.SongApi;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaylistActivity extends AppCompatActivity {
    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    androidx.appcompat.widget.Toolbar toolbar;
    RecyclerView recyclerViewdanhsachbaihat;
    FloatingActionButton floatingActionButton;
    TextView txtcollapsing;
    ImageView imgdanhsachcakhuc;
    ArrayList<Song> mangbaihat;
    PlaylistSongAdapter danhsachbaihatAdapter;
    ImageView btnThemnhac;
    SwipeRefreshLayout swipeRefreshLayout;
    private int id;

    private static ArrayList<Song> songArrayList = new ArrayList<>();
    private static ArrayList<Artist> artistArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remove_activity_danhsachbaihat);
        getDataFromIntent();
        AnhXa();
        overridePendingTransition(R.anim.anim_intent_in, R.anim.anim_intent_out);
        GetDataPlaylist();
    }

    private void GetDataPlaylist() {
        Keyword k = new Keyword();
        k.setKeyword(artistArrayList.get(0).getIdNgheSi());

        SongApi songApi = ApiClient.self().retrofit.create(SongApi.class);
        Call<List<Song>> callback = songApi.getSongByArtist(k);
        callback.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                mangbaihat = (ArrayList<Song>) response.body();
                danhsachbaihatAdapter = new PlaylistSongAdapter(PlaylistActivity.this, mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(PlaylistActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });
    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        artistArrayList.clear();

        if (intent != null){
            if (intent.hasExtra("artist")){
                Artist artistFromIntent = intent.getParcelableExtra("artist");
                artistArrayList.add(artistFromIntent);
            }
        }
    }

    private void AnhXa() {
        coordinatorLayout = findViewById(R.id.coordinatorlayout);
        collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar);
        toolbar = findViewById(R.id.toolbardanhsachbaihat);
        recyclerViewdanhsachbaihat = findViewById(R.id.recyclerviewdanhsachbaihat);
        imgdanhsachcakhuc = findViewById(R.id.imageviewdanhsachcakhuc);
        floatingActionButton = findViewById(R.id.floatingactionbutton);
        txtcollapsing = findViewById(R.id.textViewcollapsing);
        btnThemnhac = findViewById(R.id.btnthemnhacthuvien);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}