package com.resource.bankapplication.ui.login;

import android.content.Context;

import com.resource.bankapplication.domain.UserAccount;

public class BankLoginContract {

    interface View{

        void navigationToHome(UserAccount userAccount);

        void showError(String error);

        void showProgress(boolean show);

        void setPreferences(UserAccount value);

        Context getContext();
    }
    interface Presenter{

        void login(String username, String password);

        void loadPreference();
    }
}
