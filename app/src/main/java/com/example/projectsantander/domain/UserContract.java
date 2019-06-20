package com.example.projectsantander.domain;

import com.example.projectsantander.infra.BaseCallback;

public class UserContract {
    public interface IRepository{
        void login(String username, String password, BaseCallback<User>onResult);
    }
}
