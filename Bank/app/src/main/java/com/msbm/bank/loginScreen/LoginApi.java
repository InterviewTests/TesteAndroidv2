package com.msbm.bank.loginScreen;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginApi {

    @FormUrlEncoded
    @POST("/login")
    Call<LoginResponse> login(@Field("user") String user, @Field("password") String password);
}
