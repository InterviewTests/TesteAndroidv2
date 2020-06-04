package com.gft.testegft.network;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("login/")
    Single<Response> login(@Field("username") String username,
                           @Field("password") String password);

    @GET("statements/{id}")
    Single<Response> getStatements(@Path("id") String id);
}
