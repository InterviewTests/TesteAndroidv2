package com.android.bankapp.statements.router;

import android.content.Context;

import com.android.bankapp.login.view.LoginActivity_;

public class StatementRouter implements StatementRouterInput {
    @Override
    public void goToLogin(Context context) {
        LoginActivity_.intent(context).start();
    }
}
