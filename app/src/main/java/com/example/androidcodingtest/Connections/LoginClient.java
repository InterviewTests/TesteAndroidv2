package com.example.androidcodingtest.Connections;

import com.example.androidcodingtest.models.LoginResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginClient {

    @POST("login")
    Call<LoginResponse> login(@Body HashMap loginObject);
}
