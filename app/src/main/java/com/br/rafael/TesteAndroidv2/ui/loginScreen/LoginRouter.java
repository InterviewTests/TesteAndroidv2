package com.br.rafael.TesteAndroidv2.ui.loginScreen;

import android.content.Intent;

import com.br.rafael.TesteAndroidv2.Util.Constants;
import com.br.rafael.TesteAndroidv2.data.model.LoginResponse;
import com.br.rafael.TesteAndroidv2.ui.statementScreen.StatementActivity;

import java.lang.ref.WeakReference;

interface LoginRouterInput{
    void statementScreen(LoginResponse response);
}

public class LoginRouter  implements LoginRouterInput{


    public WeakReference<LoginActivity> activity;

    @Override
    public void statementScreen(LoginResponse response) {

        Intent intent = new Intent(activity.get(), StatementActivity.class);
        intent.putExtra(Constants.INSTANCE.getExtraLoginResponse(),response);

        activity.get().startActivity(intent);
        activity.get().finish();

    }
}
