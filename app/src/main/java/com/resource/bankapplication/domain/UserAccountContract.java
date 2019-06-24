package com.resource.bankapplication.domain;

import android.content.Context;

import com.resource.bankapplication.config.BaseCallback;

public class UserAccountContract {

    public interface IRepository{
        void login(String username, String password, BaseCallback<UserAccount> onResult);
        void loadPreference(Context context, BaseCallback<UserAccount> onResult);
    }
}
