package com.luizroseiro.testeandroidv2.interfaces;

import com.luizroseiro.testeandroidv2.models.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface TAInterface {

    @FormUrlEncoded
    @POST("/api/login")
    Call<User> login(@Field("user") String user, @Field("password") String password);

}
