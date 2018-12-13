package com.example.savioguedes.testeandroid.login;

import android.content.Context;

import com.example.savioguedes.testeandroid.model.Login;
import com.example.savioguedes.testeandroid.model.UserAccount;
import com.example.savioguedes.testeandroid.repository.LoginRepository;

public class LoginPresenter implements LoginContract.Presenter{

    private LoginContract.View view;
    private Context context;

    UserAccount userAccount;

    LoginPresenter(LoginContract.View view, Context context){

        this.view = view;
        this.context = context;

        view.initView();
    }

    @Override
    public void getLoginExecute(Login login) {

        LoginRepository loginRepository = new LoginRepository(context);
        loginRepository.getLoginUserInfos(login);
    }
}
