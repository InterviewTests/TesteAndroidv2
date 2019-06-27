package com.example.appbank.ui.login;

import com.example.appbank.data.remote.model.LoginRequest;
import com.example.appbank.data.remote.model.LoginResponse;

public interface LoginContract {

    interface LoginView{

        void showError();

        void navigateToList(LoginResponse loginResponse);

        void errorUsername(String message);

        void enabledButton(boolean b);

        void errorPassword(String s);

    }

    interface LoginPresenter{

        void login(LoginRequest loginRequest);

        void validUserData(String userName);

        boolean validPasswordData(String password);
    }
}
