package com.casasw.bankapp;

import android.content.Intent;

import java.lang.ref.WeakReference;


interface LoginRouterInput {

    void passDataToNextScene(LoginViewModel viewModel, Intent intent);
}

public class LoginRouter implements LoginRouterInput{

    public static String TAG = LoginRouter.class.getSimpleName();
    WeakReference<LoginActivity> activity;



    @Override
    public void passDataToNextScene(LoginViewModel viewModel, Intent intent) {
        intent.putExtra("EXTRA_LOGIN_MODEL", viewModel);
        activity.get().startActivity(intent);
    }


}
