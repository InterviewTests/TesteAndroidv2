package br.com.douglas.fukuhara.bank.persistance;

import android.content.Context;
import android.os.Build;

public abstract class LocalStorage implements Storage {

    static final String SHARED_PREF_NAME = "BANK_LOGIN_PREFERENCE";
    static final String SHARED_PREF_LOGIN_KEY = "shared_pref_login_key";
    private static LocalStorage mInstance;

    public static LocalStorage getInstance(Context context) {
        if (mInstance == null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mInstance = new LocalStorageM(context);
            } else {
                mInstance = new LocalStoragePreM(context);
            }
        }
        return mInstance;
    }
}
