package com.example.savioguedes.testeandroid.login;

import com.example.savioguedes.testeandroid.model.Login;

public interface LoginContract {

    interface View{

        void initView();
        void passUserinfo();
    }

    interface Presenter{

        void getLoginExecute(Login login);

    }
}
