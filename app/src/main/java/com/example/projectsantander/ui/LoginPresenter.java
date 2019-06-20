package com.example.projectsantander.ui;

public class LoginPresenter implements LoginContract.Presenter{

    private LoginContract.View view;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
    }



    @Override
    public void login(String username, String password) {

    }
}
