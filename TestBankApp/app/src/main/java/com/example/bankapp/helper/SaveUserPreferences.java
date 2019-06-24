package com.example.bankapp.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class SaveUserPreferences {

    private String userName;
    private String password;
    private Context context;

    private SharedPreferences sharedPreferences;

    public SaveUserPreferences(String userName, String password, Context context) {
        this.userName = userName;
        this.password = password;
        sharedPreferences = context.getSharedPreferences("userDataBank", Context.MODE_PRIVATE);

        saveLogin();
    }

    public SaveUserPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences("userDataBank", Context.MODE_PRIVATE);
    }

    public String[] getLoginUser() {
        String[] array = new String[2];
        array[0] = sharedPreferences.getString("user", "");
        array[1] = sharedPreferences.getString("password", "");
        return array;
    }

    private void saveLogin() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user", this.userName);
        editor.putString("password", this.password);
        editor.apply();
    }

    public void clearPref() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}

