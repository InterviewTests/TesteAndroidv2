package com.example.projectsantander.ui;

import android.content.Context;

public class LoginContract {


        interface View {
            void showProgress(final boolean show);

            void showError(String error);

            void navigateToHome();

            void navigateToHomeAdmin();

            Context getContext();
        }

        interface Presenter {
            void login(String username, String password);
        }
    }

