package com.example.testeandroidv2.Service;

import com.google.gson.JsonObject;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

public interface LoginService {

    @FormUrlEncoded
    @POST("login")
    Call<JsonObject> authentication(@Field("user") String user, @Field("password") String password);
}
