package br.com.douglas.fukuhara.bank.persistance;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class LocalStorage implements Storage {

    private static final String SHARED_PREF_NAME = "BANK_LOGIN_PREFERENCE";
    private static final String SHARED_PREF_LOGIN_KEY = "shared_pref_login_key";
    private static LocalStorage mInstance;
    private static SharedPreferences mPreference;

    private LocalStorage(Context context) {
        String masterKeyAlias;

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)  {
                masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);
                mPreference = EncryptedSharedPreferences.create(
                        SHARED_PREF_NAME,
                        masterKeyAlias,
                        context,
                        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM);

                return;
            }
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
