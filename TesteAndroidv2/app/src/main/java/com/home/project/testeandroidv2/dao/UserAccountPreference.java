package com.home.project.testeandroidv2.dao;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.home.project.testeandroidv2.model.UserAccount;


public class UserAccountPreference {

    /*
    classe que manipula(adiciona, remove e recupera) nas preferência o ultimo usuário logado no app
     */

    public static void insertUserLogin(UserAccount userAccount, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("userAgency", userAccount.getAgency());
        editor.putString("userBankAccount", userAccount.getBankAccount());
        editor.putString("userName", userAccount.getName());
        editor.putString("userLogin", userAccount.getLogin());
        editor.putInt("userId", userAccount.getUserId());
        editor.apply();

    }

    public static UserAccount getUserLogin(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String userLogin = preferences.getString("userLogin", "");
        String userName = preferences.getString("userName", "");
        String userAgency = preferences.getString("userAgency", "");
        String userBankAccount = preferences.getString("userBankAccount", "");
        int userId = preferences.getInt("userId", 0);
        return new UserAccount(userId, userName, userBankAccount, userAgency, userLogin);

    }

    public static void removeUserLogin(Context contexto) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(contexto);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("userAgency");
        editor.remove("userBankAccount");
        editor.remove("userName");
        editor.remove("userLogin");
        editor.remove("userId");
        editor.apply();
    }

}
