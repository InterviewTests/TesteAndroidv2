package com.example.santanderapplication.ui.login;

import com.example.santanderapplication.data.model.LoginResponseModel;

public class BankLoginContract {
    public interface View {
        void showMessage(String error);

        void showActivity(LoginResponseModel loginResponseModel);

        void showProgress(boolean b);


    }

    interface Presenter {
        void validateLogin(String user, String password);

        boolean validatePassword(String password);

        boolean validateUser(String user);

        void eatinglogin(String user, String password);
    }
}
