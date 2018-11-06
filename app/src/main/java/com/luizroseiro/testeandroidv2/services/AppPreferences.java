package com.luizroseiro.testeandroidv2.services;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import static com.luizroseiro.testeandroidv2.utils.Statics.PREF_LOGGED_IN;
import static com.luizroseiro.testeandroidv2.utils.Statics.PREF_USER_ID;

public class AppPreferences {

    private static SharedPreferences getPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    static boolean isLoggedIn(Context context) {
        return getPreferences(context).getBoolean(PREF_LOGGED_IN, false);
    }

    public int getUserId(Context context) {
        return getPreferences(context).getInt(PREF_USER_ID, -1);
    }

    static void setLoggedIn(Context context, boolean loggedIn) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(PREF_LOGGED_IN, loggedIn);
        editor.apply();
    }

    static void setUserId(Context context, int id) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putInt(PREF_USER_ID, id);
        editor.apply();
    }

}
