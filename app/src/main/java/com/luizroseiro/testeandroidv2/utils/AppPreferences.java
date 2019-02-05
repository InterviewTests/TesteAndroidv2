package com.luizroseiro.testeandroidv2.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.luizroseiro.testeandroidv2.BuildConfig;
import com.luizroseiro.testeandroidv2.MainActivity;

import static com.luizroseiro.testeandroidv2.utils.Statics.PREF_LOGGED_IN;

public class AppPreferences {

    private static SharedPreferences getPreferences() {
        return MainActivity.getContext().getSharedPreferences(BuildConfig.APPLICATION_ID,
                Context.MODE_PRIVATE);
    }

    public static void setUserLoggedIn() {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putBoolean(PREF_LOGGED_IN, true);
        editor.apply();
    }

    public static boolean isUserLoggedIn() {
        return getPreferences().getBoolean(PREF_LOGGED_IN, false);
    }

}
