package com.resource.bankapplication.data.repository;

import com.resource.bankapplication.config.BaseCallback;
import com.resource.bankapplication.config.Repository;
import com.resource.bankapplication.data.remote.LoginService;
import com.resource.bankapplication.data.remote.model.LoginModel;
import com.resource.bankapplication.domain.UserAccount;
import com.resource.bankapplication.domain.UserAccountContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository extends Repository implements UserAccountContract.IRepository {

    @Override
    public void login(String username, String password, BaseCallback<UserAccount> onResult) {
        super.data.restApi(LoginService.class)
                .login(username, password)
                .enqueue(new Callback<LoginModel>() {
                    @Override
                    public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                        if(!response.isSuccessful()){
                            onResult.onUnsuccessful("Login invalido!");
                            return;
                        }
                        if(response.body()==null){
                            onResult.onUnsuccessful("Resultado nulo!");
                            return;
                        }
                        onResult.onSuccessful(response.body().toDomain());
                    }

                    @Override
                    public void onFailure(Call<LoginModel> call, Throwable t) {
                        onResult.onUnsuccessful(t.getMessage());
                    }
                });
    }
}
