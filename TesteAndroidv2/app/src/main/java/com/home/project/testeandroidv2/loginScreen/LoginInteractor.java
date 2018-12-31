package com.home.project.testeandroidv2.loginScreen;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.home.project.testeandroidv2.model.LoginRequest;
import com.home.project.testeandroidv2.model.LoginResponse;
import com.home.project.testeandroidv2.model.UserAccount;

interface LoginInteractorInput {
    LiveData<LoginResponse> getLoginResponse(LoginRequest loginRequest, Context context);
    void saveUserAccount(UserAccount account, Context context);
    void getUserAccount(Context context);
    void removeUserAccount(Context context);
}

public class LoginInteractor implements LoginInteractorInput {

    /*
    Recebe chamadas da LoginActivity e ViewModel
    Busca os dados necessários na LoginWorker
    Envia dados ao presenter
     */
    private LoginWorkerInput loginWorkerInput;
    public LoginPresenter output;

    private LoginWorkerInput getLoginWorkerInput() {
        if (loginWorkerInput == null) return  new LoginWorker();
        return loginWorkerInput;

    }

    @Override
    public LiveData<LoginResponse> getLoginResponse(LoginRequest loginRequest, Context context) {
        loginWorkerInput = getLoginWorkerInput();
        LiveData<LoginResponse> loginResponseLiveData = loginWorkerInput.getLoginResponseWorker(loginRequest,context);
        return loginResponseLiveData;
    }

    @Override
    public void saveUserAccount(UserAccount account, Context context) {
        loginWorkerInput = getLoginWorkerInput();
        loginWorkerInput.saveUserAccountWorker(account,context);
    }

    @Override
    public void getUserAccount(Context context) {
        loginWorkerInput = getLoginWorkerInput();
        LoginResponseActivity loginResponseActivity = new LoginResponseActivity();
        loginResponseActivity.userAccount = loginWorkerInput.getUserAccountWorker(context);
        output.presentUserLoginData(loginResponseActivity);

    }

    @Override
    public void removeUserAccount(Context context) {
        loginWorkerInput = getLoginWorkerInput();
        loginWorkerInput.removeUserAccountWorker(context);
    }
}
