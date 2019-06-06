package com.br.projetotestesantanter.refactor.loginScreen;

import android.content.Intent;

import java.lang.ref.WeakReference;

interface HomeRouterInput{
    Intent statementScreen(LoginModel.LoginResponse response);

}

public class LoginRouter implements  HomeRouterInput{

    public static String TAG = LoginRouter.class.getSimpleName();
    public WeakReference<LoginActivity> activity;

    @Override
    public Intent statementScreen(LoginModel.LoginResponse response) {
        return null;
    }
}
