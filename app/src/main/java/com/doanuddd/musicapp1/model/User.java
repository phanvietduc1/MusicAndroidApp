package com.doanuddd.musicapp1.model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    public String getName() {
        return name;
    }

    public String getGender() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
