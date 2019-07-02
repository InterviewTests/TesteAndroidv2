package com.example.santandertestebank.model.domain;

import com.example.santandertestebank.model.infra.BaseCallback;

public class UserAccountContract {

    public interface IRepository {

        void login(String username, String password, BaseCallback<UserAccount> onResult);
    }

}
