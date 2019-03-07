package br.com.kakobotasso.testeandroidv2.currency;

import android.content.Intent;

import java.lang.ref.WeakReference;

import br.com.kakobotasso.testeandroidv2.login.LoginActivity;

interface CurrencyRouterInput {
    void goToLoginScreen();
}

public class CurrencyRouter implements CurrencyRouterInput {
    public WeakReference<CurrencyActivity> activity;

    @Override
    public void goToLoginScreen() {
        Intent intent = new Intent(activity.get(), LoginActivity.class);
        activity.get().startActivity(intent);
        activity.get().finish();
    }
}
