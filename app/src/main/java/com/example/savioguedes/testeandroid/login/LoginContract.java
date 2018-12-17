package com.example.savioguedes.testeandroid.login;

import com.example.savioguedes.testeandroid.model.Login;

public interface LoginContract {

    interface View{

        void initView();
        void passUserinfo(String name, String account, String balance);
        void showProgressDialog();
        void onErroRequest();
    }

    interface Presenter{

        void getLoginExecute(Login login);
        void setPreferences(String value, String tag);
        String getPreferences(String tag);
    }
}
