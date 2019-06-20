package com.resource.bankapplication.data.remote;

import com.resource.bankapplication.data.remote.dto.UserAccountDto;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginService {

    @FormUrlEncoded
    @POST("login")
    Call<UserAccountDto> login(@Field("user") String username, @Field("password") String password);
}
