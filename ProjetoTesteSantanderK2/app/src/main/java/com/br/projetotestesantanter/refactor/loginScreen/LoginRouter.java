package com.br.projetotestesantanter.refactor.loginScreen;

import android.content.Intent;
import com.br.projetotestesantanter.refactor.statementScreen.StatementActivity;

import java.lang.ref.WeakReference;

interface HomeRouterInput{
    void statementScreen(LoginModel.Login response);

}

public class LoginRouter implements  HomeRouterInput{
    private final String EXTRA_LOGIN_RESPONSE = "loginResponse";

    public WeakReference<LoginActivity> activity;

    @Override
    public void statementScreen(LoginModel.Login response) {

        Intent intent = new Intent(activity.get(),StatementActivity.class);
        intent.putExtra(EXTRA_LOGIN_RESPONSE,response);

        activity.get().startActivity(intent);
        activity.get().finish();
    }
}
