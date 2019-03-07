package br.com.kakobotasso.testeandroidv2.login;

import android.content.Intent;
import android.os.Bundle;

import java.lang.ref.WeakReference;

import br.com.kakobotasso.testeandroidv2.currency.CurrencyActivity;

interface LoginRouterInput {
    void passDataToCurrencyScreen(Bundle bundle);
}

public class LoginRouter implements LoginRouterInput {
    public WeakReference<LoginActivity> activity;


    @Override
    public void passDataToCurrencyScreen(Bundle bundle) {
        Intent intent = new Intent(activity.get(), CurrencyActivity.class);
        intent.putExtras(bundle);

        activity.get().startActivity(intent);
    }
}
