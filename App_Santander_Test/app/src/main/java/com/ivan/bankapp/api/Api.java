package com.ivan.bankapp.api;

import com.ivan.bankapp.model.StatementList;
import com.ivan.bankapp.model.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface Api {


    @GET("statements/{id}")
    Call<StatementList> getStatements(
            @Path("id") Integer id
    );

    @FormUrlEncoded
    @POST("login")
    Call<User> login(
            @Field("user") String user,
            @Field("password") String password
    );
}
