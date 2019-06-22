package com.example.bankapp.ui.login;

import android.content.Context;

import com.example.bankapp.model.user.UserAccount;

public class LoginViewPresenter {

    public interface loginView {
        void goToHome(UserAccount user);

        void showErrorMessage(String text);

        void showProgress(boolean key);

        Context getContext();
    }

    public interface loginPresenter {
        void login(String userName, String password);
    }

}
