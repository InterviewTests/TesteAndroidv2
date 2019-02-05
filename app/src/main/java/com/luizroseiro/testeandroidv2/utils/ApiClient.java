package com.luizroseiro.testeandroidv2.utils;

import com.luizroseiro.testeandroidv2.loginScreen.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiClient {

    @POST("/login")
    @FormUrlEncoded
    Call<LoginResponse> loginUser(@Field("user") String user,
                                  @Field("password") String password);

}
