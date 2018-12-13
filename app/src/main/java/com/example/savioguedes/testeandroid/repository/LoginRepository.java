package com.example.savioguedes.testeandroid.repository;

import android.content.Context;
import android.util.Log;

import com.example.savioguedes.testeandroid.model.Login;
import com.example.savioguedes.testeandroid.model.ResponseLogin;
import com.example.savioguedes.testeandroid.model.UserAccount;
import com.example.savioguedes.testeandroid.service.Api;
import com.example.savioguedes.testeandroid.service.Manager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {

    Context context;

    public LoginRepository(Context context){

        this.context = context;
    }

    public void getLoginUserInfos(final Login login){

        Api api = Manager.serviceGenerator().create(Api.class);
        Call<ResponseLogin> call = api.login(login);

        call.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {

                if (!response.isSuccessful()){

                    Log.i("RESPOSTA", "Erro: " + response + " - Code: " + response.code());
                }
                else {

                    if (response.body() != null) {

                        UserAccount userAccount = response.body().getUserAccount();
                        //UserAccountHelper.setCurrentUser(context, login.getUser());

                        Log.i("RESPOSTA", "Sucesso: " + userAccount.getUserId());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {

                Log.i("RESPOSTA", "Falha: " +t.getMessage());
            }
        });

    }
}
