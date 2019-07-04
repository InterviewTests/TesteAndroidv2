package com.example.santandertestebank.ui.login;

import android.content.Context;

import com.example.santandertestebank.model.models.UserAccountLogin;

public class LoginContract {

    public interface View {

        Context getContext();

        void showToast(String s);

        void showUserInfo(UserAccountLogin userAccountLogin);

//        void showProgressBar();
    }

    public interface Presenter {

        void loginUser(String username, String password);

        void validatePassword(String password) throws Exception;

//        void validateEmptyLogin(String password);
//
//        void validateCpfLogin(String username);
//
//        void validateEmailLogin(String username);

        void validateLogin(String username) throws Exception;
    }
}