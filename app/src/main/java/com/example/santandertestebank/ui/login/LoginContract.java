package com.example.santandertestebank.ui.login;

import com.example.santandertestebank.model.models.UserAccountLogin;

public class LoginContract {

    public interface View {

        void showToast(String s);

        void showUserInfo(UserAccountLogin userAccountLogin);
    }

    public interface Presenter {

        void loginUser(String username, String password);

    }
}