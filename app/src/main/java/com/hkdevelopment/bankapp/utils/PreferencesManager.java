package com.hkdevelopment.bankapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.hkdevelopment.bankapp.R;

public class PreferencesManager implements PreferencesManagerInt{
    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor sharedEdit;

    public PreferencesManager(Context context){
        this.context=context;
        sharedPreferences=context.getSharedPreferences(context.getString(R.string.userPrefs),Context.MODE_PRIVATE);
    }

    @Override
    public String getString(String key){
        return sharedPreferences.getString(key,"");
    }

    @Override
    public boolean getBoolean(String key){
        return sharedPreferences.getBoolean(key,false);
    }

    @Override
    public void putString(String key, String data){
        sharedEdit=sharedPreferences.edit();
        sharedEdit.putString(key,data);
        sharedEdit.apply();
    }

    @Override
    public void putBoolean(String key, boolean data){
        sharedEdit=sharedPreferences.edit();
        sharedEdit.putBoolean(key,data);
        sharedEdit.apply();
    }
}
