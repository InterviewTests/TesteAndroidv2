package com.testeandroidv2.controller;


import com.orhanobut.hawk.Hawk;
import com.testeandroidv2.App;
import com.testeandroidv2.contract.presenter.LoginPresenter;
import com.testeandroidv2.contract.view.LoginView;
import com.testeandroidv2.model.User;
import com.testeandroidv2.repository.LoginService;
import com.testeandroidv2.repository.RetrofitInstance;
import com.testeandroidv2.repository.response.Error;
import com.testeandroidv2.repository.response.UserAccountResponse;
import com.testeandroidv2.utility.Validation;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class LoginController implements LoginPresenter {

    private LoginView loginView;

    public LoginController(LoginView loginView){
        this.loginView = loginView;
    }

    @Override
    public void validateCredentials(User user) {
        loginView.showProgress();

        String userCode = user.getUser()
                .replace(
                ".", "")
                .replace("-","");

        if(Validation.isNumeric(userCode)){
            if(!Validation.isValidCPF(user.getUser())) {
                loginView.showMessageInvalidCPF();
                loginView.dismissProgress();
            }
            else
                doLogin(user);
        } else {

            /*if(!Validation.isValidEmail(user.getUser())) {
                loginView.showMessageInvalidEmail();
                loginView.dismissProgress();
            }
            else*/
                doLogin(user);
        }

    }

    private void doLogin(User user){

        LoginService loginService = RetrofitInstance.createService(LoginService.class, "user", "secretpassword");
        Call<UserAccountResponse> call = loginService.getLogin(user.getUser(), user.getPassword());

        call.enqueue(new Callback<UserAccountResponse >() {
            @Override
            public void onResponse(Call<UserAccountResponse> call, Response<UserAccountResponse> response) {

                if (response.isSuccessful()) {

                    Error error = response.body().getError();

                    if(error.getCode() ==  null) {

                        UserAccountResponse userAccountResponse = response.body();
                        App.userAccount = userAccountResponse.getUserAccount();
                        Hawk.put("idUser", userAccountResponse.getUserAccount().getUserId());
                        loginView.dismissProgress();
                        loginView.showLoggedInInterface();

                    } else {
                        App.error = response.body().getError();
                        loginView.dismissProgress();
                        loginView.showErrorActivity();
                    }
                } else {
                    UserAccountResponse userAccountResponse = response.body();
                    App.error = userAccountResponse.getError();
                    loginView.dismissProgress();
                    loginView.showErrorActivity();
                }
            }

            @Override
            public void onFailure(Call<UserAccountResponse> call, Throwable t) {
                Error error = new Error();
                error.setCode("Error");
                error.setMessage(t.getMessage());
                App.error = error;
                loginView.dismissProgress();
                loginView.showErrorActivity();
            }
        });
    }

}
