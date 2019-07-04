package com.example.santanderapplication.service;

import com.example.santanderapplication.data.model.LoginRequestModel;
import com.example.santanderapplication.data.model.LoginResponseModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IApiLogin {

    @POST("login")
    Call<LoginResponseModel> postLoginApi(@Body LoginRequestModel loginRequestModel);
}
