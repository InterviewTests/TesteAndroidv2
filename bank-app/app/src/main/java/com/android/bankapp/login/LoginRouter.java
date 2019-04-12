package com.android.bankapp.login;

import android.content.Context;

import com.android.bankapp.statements.StatementsActivity_;

import java.lang.ref.WeakReference;

public class LoginRouter implements LoginRouterInput {
    public WeakReference<LoginActivity> activity;

    @Override
    public void goToNextScreen(Context context) {
        StatementsActivity_.intent(context).start();
    }
}
