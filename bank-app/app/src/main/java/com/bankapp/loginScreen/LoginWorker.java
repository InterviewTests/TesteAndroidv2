package com.bankapp.loginScreen;

import android.content.Context;

import androidx.annotation.NonNull;

import com.bankapp.ErrorResponse;
import com.bankapp.api.ApiModule;
import com.bankapp.api.RequestListener;
import com.bankapp.api.Service;
import com.bankapp.helper.ConstantsHelper;
import com.bankapp.helper.SecurePreferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

interface LoginWorkerInput {
    public void login(LoginRequest request, RequestListener<LoginResponse> responseListener);
    public LoginResponse getUserFromSharedPreferences(Context context);
    public void setUserFromSharedPreferences(Context context, LoginRequest loginRequest);
}

public class LoginWorker implements LoginWorkerInput {

    ApiModule service = Service.createService(ApiModule.class);
    SecurePreferences preferences;

    @Override
    public void login(LoginRequest request, RequestListener<LoginResponse> responseListener){
        service.login(request.loginModel.user,request.loginModel.password).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()) {
                    responseListener.onSuccess(response.body());
                } else {
                    responseListener.onFailure(response.body());
                }
            }
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                responseListener.onFailure(new ErrorResponse("Ops! Algo deu errado ao tentar logar!"));
            }
        });
    }

    @Override
    @NonNull
    public LoginResponse getUserFromSharedPreferences(Context context) {
        LoginResponse loginResponse = new LoginResponse();
        preferences = new SecurePreferences(context, ConstantsHelper.BASE_PREFERENCES, ConstantsHelper.SECRETE_KEY, true);
        LoginModel loginModel = new LoginModel(preferences.getString(ConstantsHelper.USER_PREF), preferences.getString(ConstantsHelper.PASS_PREF));
        loginResponse.loginModel = loginModel;
        return loginResponse;
    }

    @Override
    public void setUserFromSharedPreferences(Context context, LoginRequest loginRequest) {
        preferences = new SecurePreferences(context, ConstantsHelper.BASE_PREFERENCES, ConstantsHelper.SECRETE_KEY, true);
        preferences.put(ConstantsHelper.USER_PREF, loginRequest.loginModel.user);
        preferences.put(ConstantsHelper.PASS_PREF, loginRequest.loginModel.password);
    }
}
