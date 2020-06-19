package com.example.testeandroidv2.loginScreen;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.example.testeandroidv2.Service.Api;
import com.example.testeandroidv2.Service.LoginService;

import java.io.IOException;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Retrofit;

interface LoginWorkerInput {
    void buildRequest(LoginRequest request);
    LoginResponse getLoginResponse(Callback<JsonObject> callback);
}

public class LoginWorker implements LoginWorkerInput {

    private Call<JsonObject> call;

    @Override
    public void buildRequest(LoginRequest request) {
        Retrofit api = Api.getRetrofitInstance("https://bank-app-test.herokuapp.com/api/");
        LoginService loginService = api.create(LoginService.class);
        call = loginService.authentication(request.user, request.password);
    }

    @Override
    public LoginResponse getLoginResponse(Callback<JsonObject> callback) {
        try {
            if(callback == null)
                return new Gson().fromJson(call.execute().body(), LoginResponse.class);
            else
                call.enqueue(callback);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
