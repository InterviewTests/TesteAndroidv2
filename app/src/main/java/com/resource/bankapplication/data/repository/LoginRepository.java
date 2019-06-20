package com.resource.bankapplication.data.repository;

import com.resource.bankapplication.config.BaseCallback;
import com.resource.bankapplication.config.Repository;
import com.resource.bankapplication.data.remote.LoginService;
import com.resource.bankapplication.data.remote.dto.UserAccountDto;
import com.resource.bankapplication.domain.UserAccountContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository extends Repository implements UserAccountContract.IRepository {

    @Override
    public void login(String username, String password, BaseCallback<com.resource.bankapplication.domain.UserAccount> onResult) {
        super.data.restApi(LoginService.class)
                .login(username, password)
                .enqueue(new Callback<UserAccountDto>() {
                    @Override
                    public void onResponse(Call<UserAccountDto> call, Response<UserAccountDto> response) {
                        if(response.body().getError().getCode()==0)
                            onResult.onSuccessful(response.body().getUserAccount().toDomain());
                        else
                            onResult.onUnsuccessful(response.body().getError().getMessage());
                    }

                    @Override
                    public void onFailure(Call<UserAccountDto> call, Throwable t) {
                        onResult.onUnsuccessful(t.getMessage());
                    }
                });
    }
}
