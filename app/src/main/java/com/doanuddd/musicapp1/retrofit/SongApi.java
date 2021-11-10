package com.doanuddd.musicapp1.retrofit;

import com.doanuddd.musicapp1.model.Song;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;

public interface SongApi {
//    @FormUrlEncoded
    @GET("/api/getSong")
    Call<List<Song>> getSong();
}
