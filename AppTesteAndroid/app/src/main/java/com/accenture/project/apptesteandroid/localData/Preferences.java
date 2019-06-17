package com.accenture.project.apptesteandroid.localData;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Salva na SharedPreferences o 'user' do último usuário logado no aplicativo
 */
public class Preferences {

    public static void insertUserLogin(String user, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("userLogin", user);
        editor.apply();

    }

    public static String getLastUser(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString("userLogin", "");

    }

    public static void removeUserLogin(Context contexto) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(contexto);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("userLogin");
        editor.apply();
    }
}
