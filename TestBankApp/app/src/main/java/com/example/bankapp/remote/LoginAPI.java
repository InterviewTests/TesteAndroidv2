package com.example.bankapp.remote;

import com.example.bankapp.model.userAccount;
import com.example.bankapp.model.userAccountModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginAPI {
    @FormUrlEncoded
    @POST("login")
    Call<userAccountModel> login(
            @Field("user") String userName,
            @Field("password") String password);
}
