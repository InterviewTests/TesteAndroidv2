package com.accenture.android.app.testeandroid.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.accenture.android.app.testeandroid.domain.UserAccount;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by Denis Magno on 09/07/2020.
 * denis_magno16@hotmail.com
 */
public class AuthManager {
    private static final String USER_AUTH_KEY = "USER_AUTH_KEY_JWT";
    private static final String USER_AUTH_OBJECT = "USER_AUTH_OBJECT";
    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;

    public AuthManager(Context context) {
        this.sharedPreferences = context.getSharedPreferences(USER_AUTH_KEY, 0);
        this.editor = this.sharedPreferences.edit();
    }

    public void setUserAccount(UserAccount userAccount) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(userAccount);
            this.editor.putString(USER_AUTH_OBJECT, json);
            this.editor.apply();
        } catch (JsonProcessingException var5) {
            var5.printStackTrace();
        }
    }

    public UserAccount getUserAccount() {
        UserAccount userAccount = new UserAccount();

        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = this.sharedPreferences.getString(USER_AUTH_OBJECT, new UserAccount().toString());
            userAccount = (UserAccount) mapper.readValue(json, UserAccount.class);
        } catch (JsonProcessingException var4) {
            var4.printStackTrace();
        }

        return userAccount;
    }

    public void clearUserAccount() {
        this.editor.remove(USER_AUTH_OBJECT);
        this.editor.commit();
    }
}
