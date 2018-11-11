package com.casasw.bankapp;

import android.content.Context;

interface LoginWorkerInput  {
    String getLoginData(LoginModel login, Context context);
}

public class LoginWorker implements LoginWorkerInput {
    private static final String TAG = "LoginWorker";
    @Override
    public String getLoginData(LoginModel login, Context context) {



        return null;
    }
}
