package com.casasw.bankapp;

import android.content.Context;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

interface LoginWorkerInput  {
    String getLoginData();
    public void setLoginData(LoginModel login, Context context);
}

public class LoginWorker implements LoginWorkerInput {
    private static final String TAG = "LoginWorker";
    String mJSON;
    @Override
    public String getLoginData() {
        return mJSON;
    }
    public void setLoginData(LoginModel login, Context context) {
        Ion.with(context)
                .load("https://bank-app-test.herokuapp.com/api/login")
                .setBodyParameter("user", "test_user")
                .setBodyParameter("password", "Test@1")
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        mJSON = result;
                    }
                });
    }
}
