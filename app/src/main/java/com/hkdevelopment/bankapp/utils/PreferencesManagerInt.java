package com.hkdevelopment.bankapp.utils;

public interface PreferencesManagerInt {

    void putString(String key,String data);

    void putBoolean(String key,boolean data);

    String getString(String key);

    boolean getBoolean(String key);
}
