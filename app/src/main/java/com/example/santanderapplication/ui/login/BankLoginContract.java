package com.example.santanderapplication.ui.login;

import com.example.santanderapplication.data.model.LoginRequestModel;
import com.example.santanderapplication.service.IApiLogin;

public class BankLoginContract {
    interface View{
        void showMessage(String error);

    }

    interface Presenter{
        void validateLogin(String user , String password);
        boolean validatePassword(String password);
        boolean validateUser(String user);
        void validateIApi();
    }
}
