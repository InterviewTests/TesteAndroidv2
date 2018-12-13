package com.hkdevelopment.bankapp.retrofit.services;

import com.hkdevelopment.bankapp.home.model.Statement;
import com.hkdevelopment.bankapp.login.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Services {

    @FormUrlEncoded
    @POST("login")
    Call<User> getUser(@Field("user") String name,@Field("password")String password);


    @GET("statements/{id}")
    Call<Statement> getStatements(@Path("id") String id);
}
