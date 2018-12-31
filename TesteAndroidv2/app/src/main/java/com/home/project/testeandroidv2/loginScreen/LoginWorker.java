package com.home.project.testeandroidv2.loginScreen;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import com.home.project.testeandroidv2.dao.UserAccountPreference;
import com.home.project.testeandroidv2.model.LoginRequest;
import com.home.project.testeandroidv2.model.LoginResponse;
import com.home.project.testeandroidv2.model.UserAccount;
import com.home.project.testeandroidv2.service.ConfigService;
import com.home.project.testeandroidv2.service.UserAccountDataService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

interface LoginWorkerInput {
    LiveData<LoginResponse> getLoginResponseWorker(LoginRequest loginRequest, Context context);
    void saveUserAccountWorker(UserAccount account, Context context);
    UserAccount getUserAccountWorker(Context context);
    void removeUserAccountWorker(Context context);
}

public class LoginWorker implements LoginWorkerInput {

    /*
    Classe que faz o trabalho pesado, recupera os dados de um serviço web ou de uma base de dados local
     */

    @Override
    public LiveData<LoginResponse> getLoginResponseWorker(LoginRequest loginRequest, Context context) {
        UserAccountDataService userAccountDataService = ConfigService.getConfig().create(UserAccountDataService.class);
        Call<LoginResponse> loginResponseCall = userAccountDataService.login(loginRequest);
        MutableLiveData<LoginResponse> loginRequestMutableLiveData = new MutableLiveData<>();
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();
                loginRequestMutableLiveData.setValue(loginResponse);
                if (!getUserAccountWorker(context).getName().isEmpty()) {
                    removeUserAccountWorker(context);
                }

                loginResponse.getUserAccount().setLogin(loginRequest.getUser());
                saveUserAccountWorker(loginResponse.getUserAccount(), context);
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                loginRequestMutableLiveData.setValue(null);
            }
        });

        return loginRequestMutableLiveData;
    }


    /*
    adiciona, remove e obtém nas Preferências o último usuário logado
     */
    @Override
    public void saveUserAccountWorker(UserAccount userAccount, Context context) {
        UserAccountPreference.insertUserLogin(userAccount, context);
    }

    @Override
    public UserAccount getUserAccountWorker(Context context) {
        return UserAccountPreference.getUserLogin(context);
    }

    @Override
    public void removeUserAccountWorker(Context context) {
        UserAccountPreference.removeUserLogin(context);
    }
}
