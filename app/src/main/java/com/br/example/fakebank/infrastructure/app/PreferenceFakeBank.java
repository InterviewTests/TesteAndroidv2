package com.br.example.fakebank.infrastructure.app;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceFakeBank {
    private static final String KEY_USER_NAME = "userName";
    private static final String KEY_USER_PASSWORD = "userPassword";

    private SharedPreferences mSharedPreferences;

    public PreferenceFakeBank(Context context) {
        mSharedPreferences = context.getSharedPreferences("bank",Context.MODE_PRIVATE);
    }

    public void setUserName(String user){
        mSharedPreferences.edit().putString(KEY_USER_NAME, user).apply();
    }

    public String getUserName(){
        return mSharedPreferences.getString(KEY_USER_NAME, "");
    }

    public void setUserPassword(String userPassword){
        mSharedPreferences.edit().putString(KEY_USER_PASSWORD, userPassword).apply();
    }

    public String getUserPassword(){
        return mSharedPreferences.getString(KEY_USER_PASSWORD, "");
    }

}
