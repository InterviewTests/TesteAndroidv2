package com.riso.zup.bank.loginScreen;

import com.riso.zup.bank.models.UserInfo;

public interface LoginInteractor {

    interface View{
        void loginOK(String user, UserInfo userInfo);
        void loginError(Error error);
        void loginError(int error);
    }

    interface Presenter{
        void login (String user, String password);
    }
}
