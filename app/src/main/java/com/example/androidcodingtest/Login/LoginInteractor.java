package com.example.androidcodingtest.Login;

import com.example.androidcodingtest.models.Error;

public interface LoginInteractor {

    interface View{
        void loginSuccess(String user);
        void loginError(Error error);
        void loginError(int error);
    }

    interface Presenter{
        void login(String user, String password);
    }
}
