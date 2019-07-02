package com.example.santandertestebank.model.infra.service;

import com.example.santandertestebank.model.models.ObjectsLogin;
import com.example.santandertestebank.model.models.ObjectsStatements;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    public static final String BASE_URL = "https://bank-app-test.herokuapp.com/api/";

    @GET("statements/{id}")
    Call<ObjectsStatements> bringStatements(
            @Path("id") long id);

    @FormUrlEncoded
    @POST("login")
    Call<ObjectsLogin> loginUSer(
            @Field("user") String user,
            @Field("password") String password);

}