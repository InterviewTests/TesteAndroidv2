package com.bilulo.androidtest04.data.api.login;

import com.bilulo.androidtest04.data.model.response.LoginResponse;
import com.bilulo.androidtest04.data.model.signature.LoginSignature;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginApi {
    @POST("/api/login")
    Call<LoginResponse> login(@Body LoginSignature loginSignature);
}
