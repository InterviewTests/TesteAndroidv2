package com.example.santandertestebank.ui.login;

import android.util.Patterns;

import com.example.santandertestebank.R;
import com.example.santandertestebank.model.models.ObjectsLogin;
import com.example.santandertestebank.model.repository.LoginRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View view;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void loginUser(final String username, final String password) {
        LoginRepository loginRepository = new LoginRepository ();
        final Call<ObjectsLogin> requestLogin = loginRepository.loginUser (username, password);
        requestLogin.enqueue (new Callback<ObjectsLogin> () {
            @Override
            public void onResponse(Call<ObjectsLogin> call, Response<ObjectsLogin> response) {
                if (!response.isSuccessful ()) {
                    view.showToast (response.body ().getErrorLogin ().getMessage ());
                } else {
                    try {
                        validateLogin (username);
                        validatePassword (password);
                        ObjectsLogin login = response.body ();
                        view.showUserInfo (login.getUserAccountLogin ());

                    } catch (Exception e) {
                        view.showToast (e.getMessage ());
                    }
                }
            }

            @Override
            public void onFailure(Call<ObjectsLogin> call, Throwable t) {
                view.showToast (t.getMessage ());
            }
        });
    }

    public void validatePassword(String password) throws Exception {
        if (password.trim ().isEmpty () || password.isEmpty ()) {
            throw new Exception (view.getContext ().getString (R.string.type_password_to_login));

        } else if (!password.matches ("^((?=.[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!*?])(?=\\S+$).{3,})$")) {
            throw new Exception (view.getContext ().getString (R.string.password_validation_message));
        }
    }

    @Override
    public void validateLogin(String username) throws Exception {
        if (username.trim ().isEmpty () || username.isEmpty ()) {
            throw new Exception (view.getContext ().getString (R.string.type_to_login));

        } else if (!Patterns.EMAIL_ADDRESS.matcher (username).matches ()) {
//                ("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}\\@" +
//                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+")
//                || !username.matches () { FAZER A VALIDAÇÃO DE CPF!!!!!!!!!!!!!
            throw new Exception (view.getContext ().getString (R.string.type_valid_username));
        }
    }

}