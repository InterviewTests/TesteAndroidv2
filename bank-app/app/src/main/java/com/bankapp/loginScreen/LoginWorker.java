package com.bankapp.loginScreen;

import android.content.Context;

import androidx.annotation.NonNull;

import com.bankapp.api.RequestListener;


interface LoginWorkerInput {
    public void login(LoginRequest request, RequestListener<LoginResponse> responseListener);
    public LoginResponse getUserFromSharedPreferences(Context context);
    public void setUserFromSharedPreferences(Context context, LoginRequest loginRequest);
}

public class LoginWorker implements LoginWorkerInput {

    @Override
    public void login(LoginRequest request, RequestListener<LoginResponse> responseListener){
        //TODO:
    }

    @Override
    @NonNull
    public LoginResponse getUserFromSharedPreferences(Context context) {
       //TODO
        return new LoginResponse();
    }

    @Override
    public void setUserFromSharedPreferences(Context context, LoginRequest loginRequest) {

    }
}
