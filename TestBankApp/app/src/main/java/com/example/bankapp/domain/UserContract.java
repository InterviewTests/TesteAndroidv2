package com.example.bankapp.domain;

import com.example.bankapp.helper.BaseCallback;
import com.example.bankapp.data.remote.model.user.UserAccountModel;

public class UserContract {
    public interface IRepository{
        void login(String userName, String password, BaseCallback<UserAccountModel> result);
    }
}
