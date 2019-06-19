package com.resource.bankapplication.domain;

import com.resource.bankapplication.config.BaseCallback;

public class UserAccountContract {

    public interface IRepository{
        void login(String username, String password, BaseCallback<UserAccount> onResult);
    }
}
