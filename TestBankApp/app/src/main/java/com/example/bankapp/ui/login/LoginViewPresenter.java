package com.example.bankapp.ui.login;

public class LoginViewPresenter {

    public interface loginView{
        void goToHome();
        void showErrorMessage(String text);
    }

    public interface loginPresenter{
        void login(String userName, String password);
    }

}
