package br.com.douglas.fukuhara.bank.persistance;

import android.content.Context;
import android.content.SharedPreferences;

public class LocalStorage implements Storage {

    private static final String SHARED_PREF_NAME = "BANK_LOGIN_PREFERENCE";
    private static final String SHARED_PREF_LOGIN_KEY = "shared_pref_login_key";
    private static LocalStorage mInstance;
    private static SharedPreferences mPreference;

    private LocalStorage(Context context) {
        mPreference = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }

    public static LocalStorage getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new LocalStorage(context);
        }
        return mInstance;
    }

    @Override
    public void saveLogin(String login) {
        SharedPreferences.Editor editor = mPreference.edit();
        editor.putString(SHARED_PREF_LOGIN_KEY, login);
        editor.apply();
    }

    @Override
    public String getLogin() {
        return mPreference.getString(SHARED_PREF_LOGIN_KEY, "");
    }
}
