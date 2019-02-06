package com.luizroseiro.testeandroidv2.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.luizroseiro.testeandroidv2.BuildConfig;
import com.luizroseiro.testeandroidv2.MainActivity;

import static com.luizroseiro.testeandroidv2.utils.Statics.PREF_LOGGED_IN;
import static com.luizroseiro.testeandroidv2.utils.Statics.PREF_USER_ACCOUNT;
import static com.luizroseiro.testeandroidv2.utils.Statics.PREF_USER_AGENCY;
import static com.luizroseiro.testeandroidv2.utils.Statics.PREF_USER_BALANCE;
import static com.luizroseiro.testeandroidv2.utils.Statics.PREF_USER_ID;
import static com.luizroseiro.testeandroidv2.utils.Statics.PREF_USER_NAME;

public class AppPreferences {

    private static SharedPreferences getPreferences() {
        return MainActivity.getContext().getSharedPreferences(BuildConfig.APPLICATION_ID,
                Context.MODE_PRIVATE);
    }

    public static void clearUser() {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.clear();
        editor.apply();
    }

    public static void setUserLoggedIn() {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putBoolean(PREF_LOGGED_IN, true);
        editor.apply();
    }

    public static boolean isUserLoggedIn() {
        return getPreferences().getBoolean(PREF_LOGGED_IN, false);
    }

    public static void setUserId(int userId) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putInt(PREF_USER_ID, userId);
        editor.apply();
    }

    public static int getUserId() {
        return getPreferences().getInt(PREF_USER_ID, -1);
    }

    public static void setUserName(String name) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putString(PREF_USER_NAME, name);
        editor.apply();
    }

    public static String getUserName() {
        return getPreferences().getString(PREF_USER_NAME, "");
    }

    public static void setUserAgency(String agency) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putString(PREF_USER_AGENCY, agency);
        editor.apply();
    }

    public static String getUserAgency() {
        return getPreferences().getString(PREF_USER_AGENCY, "");
    }

    public static void setUserAccount(String account) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putString(PREF_USER_ACCOUNT, account);
        editor.apply();
    }

    public static String getUserAccount() {
        return getPreferences().getString(PREF_USER_ACCOUNT, "");
    }

    public static void setUserBalance(float balance) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putFloat(PREF_USER_BALANCE, balance);
        editor.apply();
    }

    public static float getUserBalance() {
        return getPreferences().getFloat(PREF_USER_BALANCE, 0f);
    }

}
