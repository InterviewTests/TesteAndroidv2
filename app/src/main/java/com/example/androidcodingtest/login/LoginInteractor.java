package com.example.androidcodingtest.login;

import com.example.androidcodingtest.models.Error;
import com.example.androidcodingtest.models.UserAccount;

public interface LoginInteractor {

    interface View{
        void loginSuccess(String user, UserAccount userAccount);
        void loginError(Error error);
        void loginError(int error);
    }

    interface Presenter{
        void login(String user, String password);
    }
}
