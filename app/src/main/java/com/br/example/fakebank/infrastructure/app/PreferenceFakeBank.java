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

    public String getUserName(){
        return mSharedPreferences.getString(KEY_USER_NAME, "");
    }
    public String getUserPassword(){
        return mSharedPreferences.getString(KEY_USER_PASSWORD, "");
    }

    public void setNewPreference(String userName, String passName){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(KEY_USER_NAME, userName);
        editor.putString(KEY_USER_PASSWORD, passName);
        editor.apply();
    }

}
