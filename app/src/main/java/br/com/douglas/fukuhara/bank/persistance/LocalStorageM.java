package br.com.douglas.fukuhara.bank.persistance;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class LocalStorageM extends LocalStorage {

    private static SharedPreferences mPreference;

    public LocalStorageM(Context context) {
        String masterKeyAlias;
        try {
            masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);
            mPreference = EncryptedSharedPreferences.create(
                    SHARED_PREF_NAME,
                    masterKeyAlias,
                    context,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM);
            return;
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        throw new RuntimeException("Falha ao inicializar o Encrypted Shared Preferences (Android M e superior)");
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
