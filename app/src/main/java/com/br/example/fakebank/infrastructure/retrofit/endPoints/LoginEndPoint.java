package com.br.example.fakebank.infrastructure.retrofit.endPoints;

import com.br.example.fakebank.infrastructure.retrofit.responses.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginEndPoint {

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> makeRequestLogin(@Field("user") String user, @Field("password")String password);
}
