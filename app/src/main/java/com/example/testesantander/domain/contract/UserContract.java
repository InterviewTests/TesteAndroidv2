package com.example.testesantander.domain.contract;

import com.example.testesantander.infra.BaseCallback;
import com.example.testesantander.login.LoginResponse;

public class UserContract {
    public interface IUserRepository{
        void login(String username, String password, BaseCallback<LoginResponse> onResult);
    }
}
