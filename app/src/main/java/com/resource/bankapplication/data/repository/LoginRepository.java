package com.resource.bankapplication.data.repository;

import android.content.Context;

import com.resource.bankapplication.ConstantsApp;
import com.resource.bankapplication.config.BaseCallback;
import com.resource.bankapplication.config.Repository;
import com.resource.bankapplication.data.local.LoginSharedPref;
import com.resource.bankapplication.data.remote.LoginService;
import com.resource.bankapplication.data.remote.dto.UserAccountDto;
import com.resource.bankapplication.domain.UserAccount;
import com.resource.bankapplication.domain.UserAccountContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository extends Repository implements UserAccountContract.IRepository {

    private static LoginSharedPref sharedPref;

    @Override
    public void login(String username, String password, BaseCallback<UserAccount> onResult) {
        super.data.restApi(LoginService.class)
                .login(username, password)
                .enqueue(new Callback<UserAccountDto>() {
                    @Override
                    public void onResponse(Call<UserAccountDto> call, Response<UserAccountDto> response) {
                        if(response.body().getError().getCode()!=0)
                            onResult.onUnsuccessful(response.body().getError().getMessage());
                        else{
                            sharedPref.saveSharedPref(username, password);
                            onResult.onSuccessful(response.body().getUserAccount().toDomain());
                        }
                    }

                    @Override
                    public void onFailure(Call<UserAccountDto> call, Throwable t) {
                        onResult.onUnsuccessful(ConstantsApp.NO_CONNECTION);
                    }
                });
    }

    @Override
    public void loadPreference(Context context, BaseCallback<UserAccount> onResult) {
        sharedPref = new LoginSharedPref(context);
        onResult.onSuccessful(new UserAccount(sharedPref.getUsername(), sharedPref.getPassword()));
    }
}
