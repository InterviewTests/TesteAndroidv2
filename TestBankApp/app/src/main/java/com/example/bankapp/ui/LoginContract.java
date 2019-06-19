package com.example.bankapp.ui;

public class LoginContract {

    public interface loginView{
        void goToHome();
        void showErrorMessage(String text);
    }

    public interface loginPresenter{
        void login(String userName, String password);
    }

}
