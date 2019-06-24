package com.resource.bankapplication.data.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class LoginSharedPref{

    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String username;
    private String password;

    public LoginSharedPref(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences( context );
        editor = sharedPreferences.edit();
        username = sharedPreferences.getString(USERNAME, "");
        password = sharedPreferences.getString(PASSWORD, "");
    }

    public void saveSharedPref(String username, String password){
        if(username.isEmpty()|| !username.equals(this.username)) {
            editor.putString(USERNAME, username);
            this.username = username;
        }
        if(password.isEmpty()|| !password.equals(this.password)) {
            editor.putString(PASSWORD, password);
            this.password = password;
        }
        editor.commit();
    }

    public String getUsername() {
        return username!=null ? username:"";
    }

    public String getPassword() {
        return password!=null ? password:"";
    }
}
