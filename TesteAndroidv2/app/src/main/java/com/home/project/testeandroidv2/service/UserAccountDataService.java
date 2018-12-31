package com.home.project.testeandroidv2.service;

import com.home.project.testeandroidv2.model.LoginResponse;
import com.home.project.testeandroidv2.model.LoginRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserAccountDataService {

    @POST("login")
    Call<LoginResponse> login(@Body LoginRequest user);
}
