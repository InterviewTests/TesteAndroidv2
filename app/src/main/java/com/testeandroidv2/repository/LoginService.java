package com.testeandroidv2.repository;

import com.testeandroidv2.repository.response.StatementResponse;
import com.testeandroidv2.repository.response.UserAccountResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface LoginService {

    @FormUrlEncoded
    @POST("login")
    Call<UserAccountResponse> getLogin(
            @Field("user") String user,
            @Field("password") String password);

    @GET("statements/{idUser}")
    Call<StatementResponse> getStatements(@Path("idUser") String idUser);
}
