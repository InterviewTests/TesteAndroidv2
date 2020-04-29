package com.br.web.glix.interviewgiovanipaleologo.loginScreen;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface LoginScreenAPI {

    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @POST("login")
    Call<LoginScreenResponse> login(@Field("user") String user, @Field("password") String password);
}
