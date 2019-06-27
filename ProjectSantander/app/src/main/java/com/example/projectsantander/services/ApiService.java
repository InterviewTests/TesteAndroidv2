package com.example.projectsantander.services;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @POST("login")
    @FormUrlEncoded
    Call<LoginResponse> login(@Field("user") String user, @Field("password") String password);

    @GET("statements/{id}")
    Call<TransacaoResponse> transacoes(@Path("id") int id);
}
