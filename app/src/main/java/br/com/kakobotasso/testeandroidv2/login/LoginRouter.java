package br.com.kakobotasso.testeandroidv2.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import java.lang.ref.WeakReference;

import br.com.kakobotasso.testeandroidv2.currency.CurrencyActivity;
import br.com.kakobotasso.testeandroidv2.util.ScreenKeys;
import br.com.kakobotasso.testeandroidv2.util.SharedPrefs;

interface LoginRouterInput {
    void passDataToCurrencyScreen(Bundle bundle);

    void loadCurrencyScreen();
}

public class LoginRouter implements LoginRouterInput {
    public WeakReference<LoginActivity> activity;


    @Override
    public void passDataToCurrencyScreen(Bundle bundle) {
        Intent intent = new Intent(activity.get(), CurrencyActivity.class);
        intent.putExtras(bundle);

        activity.get().startActivity(intent);
        activity.get().finish();
    }

    @Override
    public void loadCurrencyScreen() {
        SharedPreferences sharedPrefs = activity.get().sharedPrefs;
        Bundle bundle = new Bundle();

        bundle.putString(ScreenKeys.NAME, sharedPrefs.getString(SharedPrefs.NAME, ""));
        bundle.putString(ScreenKeys.AGENCY, sharedPrefs.getString(SharedPrefs.AGENCY, ""));
        bundle.putString(ScreenKeys.BANK_ACCOUNT, sharedPrefs.getString(SharedPrefs.BANK_ACCOUNT, ""));

        long prefsLong = sharedPrefs.getLong(SharedPrefs.BALANCE, Double.doubleToLongBits(0));
        bundle.putDouble(ScreenKeys.BALANCE, Double.longBitsToDouble(prefsLong));
        bundle.putInt(ScreenKeys.USER_ID, sharedPrefs.getInt(SharedPrefs.USER_ID, -1));

        passDataToCurrencyScreen(bundle);
    }
}
