package com.doanuddd.musicapp1.retrofit;

import com.doanuddd.musicapp1.model.Genre;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GenreApi {

    @GET("/api/getGenres")
    Call<List<Genre>> getGenre();
}
