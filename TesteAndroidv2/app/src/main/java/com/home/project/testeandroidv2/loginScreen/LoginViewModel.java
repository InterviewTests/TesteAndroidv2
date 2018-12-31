package com.home.project.testeandroidv2.loginScreen;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.support.annotation.NonNull;

import com.home.project.testeandroidv2.model.LoginRequest;
import com.home.project.testeandroidv2.model.LoginResponse;
import com.home.project.testeandroidv2.model.UserAccount;

public class LoginViewModel extends AndroidViewModel {

    private LoginInteractor loginInteractor;
    private LiveData<LoginResponse> loginResponseLiveData;
    private Application application;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        loginInteractor = new LoginInteractor();
        this.application = application;

    }

    public void login(LoginRequest loginRequest) {
        loginResponseLiveData = loginInteractor.getLoginResponse(loginRequest, application.getBaseContext());
    }

    public LiveData<LoginResponse> loginResponse() {
        return loginResponseLiveData;
    }


    public void removeUserAccount(Context context) {
        loginInteractor.removeUserAccount(context);
    }

}
