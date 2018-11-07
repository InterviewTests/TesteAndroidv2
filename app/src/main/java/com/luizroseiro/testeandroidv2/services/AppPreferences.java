package com.luizroseiro.testeandroidv2.services;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import static com.luizroseiro.testeandroidv2.utils.Statics.PREF_LOGGED_IN;
import static com.luizroseiro.testeandroidv2.utils.Statics.PREF_USER_AGENCY;
import static com.luizroseiro.testeandroidv2.utils.Statics.PREF_USER_BALANCE;
import static com.luizroseiro.testeandroidv2.utils.Statics.PREF_USER_BANK_ACCOUNT;
import static com.luizroseiro.testeandroidv2.utils.Statics.PREF_USER_ID;
import static com.luizroseiro.testeandroidv2.utils.Statics.PREF_USER_NAME;

public class AppPreferences {

    private static SharedPreferences getPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    static void setLoggedIn(Context context, boolean loggedIn) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(PREF_LOGGED_IN, loggedIn);
        editor.apply();
    }

    static boolean isLoggedIn(Context context) {
        return getPreferences(context).getBoolean(PREF_LOGGED_IN, false);
    }

    static void setUserId(Context context, int id) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putInt(PREF_USER_ID, id);
        editor.apply();
    }

    static int getUserId(Context context) {
        return getPreferences(context).getInt(PREF_USER_ID, -1);
    }

    static void setUserName(Context context, String name) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(PREF_USER_NAME, name);
        editor.apply();
    }

    public static String getUserName(Context context) {
        return getPreferences(context).getString(PREF_USER_NAME, "");
    }

    static void setUserBankAccount(Context context, String bankAccount) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(PREF_USER_BANK_ACCOUNT, bankAccount);
        editor.apply();
    }

    public static String getUserBankAccount(Context context) {
        return getPreferences(context).getString(PREF_USER_BANK_ACCOUNT, "");
    }

    static void setUserAgency(Context context, String agency) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(PREF_USER_AGENCY, agency);
        editor.apply();
    }

    public static String getUserAgency(Context context) {
        return getPreferences(context).getString(PREF_USER_AGENCY, "");
    }

    static void setUserBalance(Context context, float balance) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putFloat(PREF_USER_BALANCE, balance);
        editor.apply();
    }

    public static float getUserBalance(Context context) {
        return getPreferences(context).getFloat(PREF_USER_BALANCE, -1.0f);
    }

    static void clearUser(Context context) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.clear();
        editor.apply();
    }

}
