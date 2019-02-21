package com.avanade.testesantander2;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIRetrofitService {

    /* executa check login (user/password) */
    @FormUrlEncoded
    @POST("/api/login")
    Call<ApiPostLoginResponse> postLogin
    (
            @Field("user") String user,
            @Field("password") String password
    );

    /* Retorna uma lista com o histórico bancário do userId */
    @GET("/api/statements/{idUser}")
    Call<CurrencyResponse> getCurrency(@Path("idUser") int userId);
}