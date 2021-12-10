package com.doanuddd.musicapp1.retrofit;

import com.doanuddd.musicapp1.model.Keyword;
import com.doanuddd.musicapp1.model.Song;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface SongApi {
//    @FormUrlEncoded
    @GET("/api/getSong")
    Call<List<Song>> getSong();

//    @FormUrlEncoded
    @POST("/api/getSongByKeyWord")
    Call<List<Song>> getSongByKeyWord(@Body Keyword keyword);

    @POST("/api/getSongByArtist")
    Call<List<Song>> getSongByArtist(@Body Keyword keyword);
}
