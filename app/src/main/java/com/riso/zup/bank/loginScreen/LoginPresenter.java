package com.riso.zup.bank.loginScreen;

import com.riso.zup.bank.R;
import com.riso.zup.bank.helpers.Validator;
import com.riso.zup.bank.models.ResponseLoginUser;
import com.riso.zup.bank.network.ApiConfig;
import com.riso.zup.bank.network.Login;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements LoginInteractor.Presenter {

    private LoginInteractor.View view;

    public LoginPresenter(LoginInteractor.View view){
        this.view = view;
    }

    @Override
    public void login(final String user, String password) {

        Boolean resultUser = Validator.userValidator(user);
        Boolean resultPassword = Validator.passwordValidator(password);

        if(!resultUser){
            view.loginError(R.string.error_user_login);
            return;
        }
        else if(!resultPassword){
            view.loginError(R.string.error_password_login);
            return;
        }
        else{
            //Call conection to DataBase
            HashMap loginObject = new HashMap();
            loginObject.put("user", user);
            loginObject.put("password", password);

            Login loginClient = ApiConfig.create(Login.class);
            Call<ResponseLoginUser> call = loginClient.login(loginObject);
            call.enqueue(new Callback<ResponseLoginUser>() {
                @Override
                public void onResponse(Call<ResponseLoginUser> call, Response<ResponseLoginUser> response) {
                    ResponseLoginUser ResponseLogin = response.body();
                    if(response.isSuccessful()){
                        view.loginOK(user, ResponseLogin.getUserInfo());
                    }
                    else{
                        view.loginError(ResponseLogin.getError());
                    }
                }

                @Override
                public void onFailure(Call<ResponseLoginUser> call, Throwable t) {
                    view.loginError(null);
                }
            });
        }

    }
}
