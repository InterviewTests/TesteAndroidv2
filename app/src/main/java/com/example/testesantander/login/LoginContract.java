package com.example.testesantander.login;

import android.content.Context;

import com.example.testesantander.domain.User;

public class LoginContract {
    public interface View{

        void navigateToList(User user );

        Context getActivity();

        void enableButton(boolean b);

        void errorPassword(String s);

        void errorUsername(String s);

        void showProgress(final boolean show);


    }
    public interface Presenter{

        void login(String username, String password);

        boolean validUsername(String user);

        boolean validPassword(String password);

    }
}
