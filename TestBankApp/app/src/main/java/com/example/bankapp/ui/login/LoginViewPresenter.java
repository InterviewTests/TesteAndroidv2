package com.example.bankapp.ui.login;

import com.example.bankapp.model.user.UserAccount;
import com.example.bankapp.model.user.UserAccountModel;

public class LoginViewPresenter {

    public interface loginView{
        void goToHome(UserAccount user);
        void showErrorMessage(String text);
    }

    public interface loginPresenter{
        void login(String userName, String password);
    }

}
