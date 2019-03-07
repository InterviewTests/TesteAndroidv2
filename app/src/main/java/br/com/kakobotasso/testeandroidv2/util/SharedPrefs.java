package br.com.kakobotasso.testeandroidv2.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefs {
    private static final String PREFS_NAME = "testeandroidv2";

    public static final String USER_ID = "user_id";
    public static final String NAME = "name";
    public static final String AGENCY = "agency";
    public static final String BANK_ACCOUNT = "bank_account";
    public static final String BALANCE = "balance";

    public static final String USER = "user";
    public static final String PASSWORD = "password";

    public static SharedPreferences getSharedPrefs(Context context) {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public static boolean isLogged(SharedPreferences preferences) {
        return preferences.getInt(USER_ID, -1) != -1;
    }
}
