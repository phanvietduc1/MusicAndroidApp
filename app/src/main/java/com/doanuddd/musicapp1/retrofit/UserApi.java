package com.doanuddd.musicapp1.retrofit;

import android.util.Log;

import com.doanuddd.musicapp1.model.Keyword;
import com.doanuddd.musicapp1.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserApi {
//    @FormUrlEncoded
//    @GET("/api/user")
//    Call<List<User>> getUser();

//    @FormUrlEncoded
    @POST("api/user/login")
    Call<User> authenticate(@Body User user);

//    @FormUrlEncoded
    @POST("api/user/register")
    Call<User> register(@Body User user);

    @POST("api/user/confirmOtp")
    Call<User> confirmOtp(@Body User user);

    @POST("api/user/resetPassword")
    Call<User> resetPassword(@Body Keyword keyword);
}
