package com.example.santandertestebank.ui.login;

import com.example.santandertestebank.model.models.ObjectsLogin;
import com.example.santandertestebank.model.repository.LoginRepository;
import com.example.santandertestebank.model.service.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View view;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void loginUser(String username, String password) {
        LoginRepository loginRepository = new LoginRepository ();
        final Call<ObjectsLogin> requestLogin = loginRepository.loginUser (username, password);
        requestLogin.enqueue (new Callback<ObjectsLogin> () {
            @Override
            public void onResponse(Call<ObjectsLogin> call, Response<ObjectsLogin> response) {
                if (!response.isSuccessful ()) {
                    view.showToast (response.body ().getErrorLogin ().getMessage ());
                } else {
                    ObjectsLogin login = response.body ();
                    view.showUserInfo (login.getUserAccountLogin ());
                }
            }

            @Override
            public void onFailure(Call<ObjectsLogin> call, Throwable t) {
                view.showToast (t.getMessage ());
            }
        });

    }

}