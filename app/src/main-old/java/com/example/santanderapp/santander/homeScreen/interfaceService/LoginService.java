package com.example.santanderapp.santander.homeScreen.interfaceService;


import com.example.santanderapp.santander.homeScreen.model.RequestLogin;
import com.example.santanderapp.santander.homeScreen.model.ResponseLogin;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface LoginService {

    public static final String BASE_URL = "https://bank-app-test.herokuapp.com/api/";

    @POST("login")
    Call<ResponseLogin> login(@Body RequestLogin requestBody);

}
