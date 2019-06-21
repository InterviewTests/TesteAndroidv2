package com.resource.bankapplication.ui.login;

import com.resource.bankapplication.domain.UserAccount;

public class BankLoginContract {

    interface View{

        void navigationToHome(UserAccount userAccount);

        void showError(String error);

        void showProgress(boolean show);
    }
    interface Presenter{

        void login(String username, String password);
    }
}
