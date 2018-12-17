package com.example.savioguedes.testeandroid.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.savioguedes.testeandroid.model.Login;
import com.example.savioguedes.testeandroid.model.ResponseLogin;
import com.example.savioguedes.testeandroid.model.UserAccount;
import com.example.savioguedes.testeandroid.service.Api;
import com.example.savioguedes.testeandroid.service.Manager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements LoginContract.Presenter{

    private LoginContract.View view;
    private Context context;

    LoginPresenter(LoginContract.View view, Context context){

        this.view = view;
        this.context = context;

        view.initView();
    }

    @Override
    public void getLoginExecute(final Login login) {

        Api api = Manager.serviceGenerator().create(Api.class);
        Call<ResponseLogin> call = api.login(login);

        call.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {

                if (!response.isSuccessful()){

                    view.onErroRequest();
                    Log.i("RESPOSTA_REQUEST_LOGIN", "Erro: " + response + " - Code: " + response.code());
                }
                else {

                    if (response.body() != null) {

                        UserAccount userAccount = response.body().getUserAccount();
                        setPreferences(login.getUser(), "CURRENT_USERNAME");

                        view.passUserinfo(userAccount.getName(),
                                userAccount.getBankAccount()+" / "+userAccount.getAgency(),
                                String.valueOf(userAccount.getBalance()));

                        Log.i("RESPOSTA_REQUEST_LOGIN", "Sucesso: " + userAccount.getBankAccount());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {

                view.onErroRequest();
                Log.i("RESPOSTA_REQUEST_LOGIN", "Falha: " +t.getMessage());
            }
        });
    }

    @Override
    public void setPreferences(String value, String tag) {

        SharedPreferences.Editor editor = context.getSharedPreferences("LOCAL_SAVE", Context.MODE_PRIVATE).edit();
        editor.putString(tag, value);
        editor.apply();
    }

    @Override
    public String getPreferences(String tag) {

        String prefs = context.getSharedPreferences("LOCAL_SAVE", Context.MODE_PRIVATE)
                .getString(tag,"");

        return prefs;
    }

}
