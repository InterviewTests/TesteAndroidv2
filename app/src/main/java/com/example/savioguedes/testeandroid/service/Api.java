package com.example.savioguedes.testeandroid.service;

import com.example.savioguedes.testeandroid.model.Login;
import com.example.savioguedes.testeandroid.model.ResponseLogin;
import com.example.savioguedes.testeandroid.model.Statement;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {

    @POST("login")
    Call<ResponseLogin> login(@Body Login user_login);

    @GET("statements/{id}")
    Call<Statement> getStatements(@Path("id") String userId);
}
