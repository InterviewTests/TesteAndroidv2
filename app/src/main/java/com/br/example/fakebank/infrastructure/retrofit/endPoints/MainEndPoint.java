package com.br.example.fakebank.infrastructure.retrofit.endPoints;

import com.br.example.fakebank.infrastructure.retrofit.responses.MainResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface MainEndPoint {

    @FormUrlEncoded
    @POST("login")
    Call<MainResponse> makeRequestLogin(@Field("user") String user, @Field("password")String password);
}
