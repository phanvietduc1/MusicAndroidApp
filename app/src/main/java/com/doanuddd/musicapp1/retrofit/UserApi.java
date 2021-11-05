package com.doanuddd.musicapp1.retrofit;

import com.doanuddd.musicapp1.model.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserApi {
    @FormUrlEncoded
    @POST("/api/user/login")
    Call<User> authenticate(@Field("email") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("user/register")
    Call<User> register(@Field("name") String username, @Field("email") String password, @Field("password") String email);
}
