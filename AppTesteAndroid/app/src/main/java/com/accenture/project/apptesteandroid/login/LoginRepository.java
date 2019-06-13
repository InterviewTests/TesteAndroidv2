package com.accenture.project.apptesteandroid.login;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.accenture.project.apptesteandroid.model.LoginRequest;
import com.accenture.project.apptesteandroid.model.LoginResponse;
import com.accenture.project.apptesteandroid.service.RetrofitConfig;
import com.accenture.project.apptesteandroid.service.UserAccountService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

interface ILoginRepository{

    void getLoginResponse(LoginRequest loginRequest);
}
public class LoginRepository implements ILoginRepository{


    @Override
    public void getLoginResponse(LoginRequest loginRequest) {

        UserAccountService userAccountDataService = RetrofitConfig.getConfig().create(UserAccountService.class);
        Call<LoginResponse> loginResponseCall = userAccountDataService.login(loginRequest);

        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if(response.isSuccessful()){

                    LoginResponse loginResponse = response.body();

                    Log.d("RetrofitTeste", "onResponse: " + loginResponse.getUserAccount().getName());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.d("RetrofitTeste", "onResponse: error " + t.getMessage());

            }
        });
    }
}
