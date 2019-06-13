package com.accenture.project.apptesteandroid.service;

import com.accenture.project.apptesteandroid.model.LoginRequest;
import com.accenture.project.apptesteandroid.model.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserAccountService {

    @POST("login")
    Call<LoginResponse> login(@Body LoginRequest user);
}
