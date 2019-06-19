package com.example.bankapp.repository;

import com.example.bankapp.domain.UserContract;
import com.example.bankapp.helper.BaseCallback;
import com.example.bankapp.model.user.userAccountModel;
import com.example.bankapp.remote.BuildApi;
import com.example.bankapp.remote.LoginAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository extends BuildApi implements UserContract.IRepository {


    @Override
    public void login(String userName, String password, final BaseCallback<userAccountModel> result) {
        super.getBuild(LoginAPI.class)
                .login(userName,password).enqueue(new Callback<userAccountModel>() {
            @Override
            public void onResponse(Call<userAccountModel> call, Response<userAccountModel> response) {
                if(response.body().getError().getMessage()!=null){
                    result.onUnsuccessful(response.body().getError().getMessage());
                    return;
                }

                result.onSuccessful(response.body());
            }

            @Override
            public void onFailure(Call<userAccountModel> call, Throwable t) {
                result.onUnsuccessful(t.getMessage());
            }
        });
    }
}
