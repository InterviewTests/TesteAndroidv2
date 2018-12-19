package com.example.jcsantos.santanderteste.Modules.Login;

import android.content.Context;

import com.example.jcsantos.santanderteste.Components.Objects.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginModel {
    private LoginResponse login_response;
    private User userAccount;
    public Context cx;

    public LoginModel (LoginResponse response, Context cx) {
        this.login_response = response;
        this.cx = cx;
    }

    public void requestLogin (String user, String password) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://bank-app-test.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LoginService services = retrofit.create(LoginService.class);
        final Call<LoginModel> requestLogin;
        requestLogin = services.loginService(user, password);

        requestLogin.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                if (response.body() != null) {
                    if (response.body().userAccount != null) {
                        userAccount = response.body().userAccount;
                        login_response.loginSucess(userAccount);
                    } else {
                        System.out.println(":: login sucesso nulo ::");
                    }
                }
            }
            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                // tratar o erro...
                System.out.println(":: erro login ::" + t.getMessage());
            }
        });
    }
}
