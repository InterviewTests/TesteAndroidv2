package com.example.appbank.ui.login;

import com.example.appbank.data.remote.model.LoginRequest;

public interface LoginContract {

    interface LoginView{

        void showLogin();

    }

    interface LoginPresenter{

        void setLoginView();

        void login(LoginRequest loginRequest);

    }
}
