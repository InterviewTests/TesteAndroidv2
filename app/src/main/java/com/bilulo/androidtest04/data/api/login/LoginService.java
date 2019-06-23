package com.bilulo.androidtest04.data.api.login;

import android.util.Log;

import com.bilulo.androidtest04.data.api.ResponseListener;
import com.bilulo.androidtest04.data.api.retrofit.RetrofitInitializer;
import com.bilulo.androidtest04.data.model.response.LoginResponse;
import com.bilulo.androidtest04.data.model.signature.LoginSignature;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginService {
    private RetrofitInitializer retrofitInitializer = RetrofitInitializer.getInstance();

    public void login(String user, String password, final ResponseListener<LoginResponse> listener) {
        LoginApi loginApi = retrofitInitializer.getLoginApi();
        LoginSignature loginSignature = new LoginSignature();
        loginSignature.setUser(user);
        loginSignature.setPassword(password);
        loginApi.login(loginSignature).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    listener.onResponseSuccess(response.body());
                } else {
                    ResponseBody responseBody = response.errorBody();
                    if (responseBody != null)
                        Log.e(LoginService.class.getSimpleName(), responseBody.toString());
                    listener.onResponseError();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e(LoginService.class.getSimpleName(), t.getMessage());
                listener.onResponseError();
            }
        });
    }


}
