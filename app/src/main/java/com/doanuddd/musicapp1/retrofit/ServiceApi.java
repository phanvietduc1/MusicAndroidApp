package com.doanuddd.musicapp1.retrofit;

import com.doanuddd.musicapp1.model.Song;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ServiceApi {
    @FormUrlEncoded
    @POST("/api/searchSong")
    Call<List<Song>> getSongByKeyWord(@Body String keyword);
}
