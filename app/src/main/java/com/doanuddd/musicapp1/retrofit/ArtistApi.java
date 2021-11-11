package com.doanuddd.musicapp1.retrofit;

import com.doanuddd.musicapp1.model.Artist;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ArtistApi {

    //@FormUrlEncoded
    @GET("/api/artist")
    Call<List<Artist>> getArtist();
}
