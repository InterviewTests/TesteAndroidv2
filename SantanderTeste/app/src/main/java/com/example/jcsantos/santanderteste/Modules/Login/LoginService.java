package com.example.jcsantos.santanderteste.Modules.Login;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface LoginService {
    @Headers({"Content-Type: application/x-www-form-urlencoded"})
    @FormUrlEncoded
    @POST("login")
    Call<LoginModel> loginService(
            @Field("user") String user,
            @Field("password") String password
    );

}
