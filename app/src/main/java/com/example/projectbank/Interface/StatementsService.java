package com.example.projectbank.Interface;

import com.example.projectbank.Model.Statement;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface StatementsService {

    //    @FormUrlEncoded
    @GET("statements/{userId}")
    Call<Statement> buscarStatements(@Path("userId") int userId); //, @Field("user") String user, @Field("password") String password
}