package com.accenture.project.apptesteandroid.login;

import android.content.Context;
import android.util.Log;

import com.accenture.project.apptesteandroid.localData.Preferences;
import com.accenture.project.apptesteandroid.model.LoginRequest;
import com.accenture.project.apptesteandroid.model.LoginResponse;
import com.accenture.project.apptesteandroid.remoteData.RetrofitConfig;
import com.accenture.project.apptesteandroid.remoteData.LoginService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
   Classe responsável por buscar e manipular dados locais e da web referentes ao Login do usuário
 */
interface ILoginRepository {

    void getLoginResponse(LoginRequest loginRequest, final ILoginInteractor loginInteractor);

    void insertLastUserLogged(String user, Context context);

    String fetchLastUserLogged(Context context);

    void removeLastUserLogged(Context context);
}

public class LoginRepository implements ILoginRepository {

    @Override
    public void getLoginResponse(final LoginRequest loginRequest, final ILoginInteractor loginInteractor) {

        LoginService userAccountDataService = RetrofitConfig.getConfig().create(LoginService.class);
        Call<LoginResponse> loginResponseCall = userAccountDataService.login(loginRequest);

        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if (response.isSuccessful()) {
                    LoginResponse loginResponse = response.body();
                    //comunica ao loginInteractor os dados retornados na requisição
                    Log.d("balance", "formatBalance: " + loginResponse.getUserAccount().getName());
                    loginInteractor.loginResponseOk(loginResponse.getUserAccount(), loginRequest.getUser());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                //comunica ao loginInteractor que houve um erro na requisição
                loginInteractor.loginResponseError();

            }
        });
    }


    /*
     Manipula os dados locais (SharedPreferences)
     */

    @Override
    public void insertLastUserLogged(String user, Context context) {
        Preferences.insertUserLogin(user, context);
    }

    @Override
    public String fetchLastUserLogged(Context context) {
       return Preferences.getLastUser(context);
    }

    @Override
    public void removeLastUserLogged(Context context) {
        Preferences.removeUserLogin(context);
    }
}
