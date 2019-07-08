package ssilvalucas.testeandroidv2.data.storage;

import android.content.Context;
import android.content.SharedPreferences;

public class UserDataStorage {

    public static final String LAST_LOGIN_USER = "lastLoginUsername";

    public static void writeUsername(String username, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(LAST_LOGIN_USER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(LAST_LOGIN_USER, username);
        editor.apply();
    }

    public static String readLastUser(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(LAST_LOGIN_USER, Context.MODE_PRIVATE);
        return preferences.getString(LAST_LOGIN_USER, "");
    }
}
