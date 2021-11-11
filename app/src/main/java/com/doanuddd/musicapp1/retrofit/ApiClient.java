package com.doanuddd.musicapp1.retrofit;

import com.google.gson.Gson;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit;

    private static final String BASE_URL = "https://golang-blog-api.herokuapp.com";

    public static Retrofit getRetrofitInstance(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())  // chuyen json object thanh java object
                    .build();

        }

        return retrofit;
    }
}
