package com.example.projectbank.Interface;

import com.example.projectbank.Model.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserService {

    @FormUrlEncoded
    @POST("login")
    Call<User> buscarUser(@Field("user") String user, @Field("password") String password);
}