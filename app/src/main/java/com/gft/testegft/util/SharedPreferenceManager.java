package com.gft.testegft.util;

import com.gft.testegft.base.GftApplication;

public class SharedPreferenceManager {

    public static void setName(String key, String value) {
        GftApplication.preferences.edit().putString(key, value ).apply();
    }
    public static String getName(String key) {
        return GftApplication.preferences.getString(key, "");
    }

}
