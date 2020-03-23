package com.bankapp.api;

import com.bankapp.loginScreen.LoginResponse;
import com.bankapp.statementScreen.StatementResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/** * REST API access points. */
public interface ApiModule {

    @FormUrlEncoded
    @POST("api/login")
    @Headers({"Content-Type: application/x-www-form-urlencoded"})
    Call<LoginResponse> login(@Field("user") String user, @Field("password") String password);

    @GET("api/statements/{idUser}")
    @Headers({"Content-Type: application/x-www-form-urlencoded"})
    Call<StatementResponse> getStatemets(@Path("idUser") long idUser);

}
