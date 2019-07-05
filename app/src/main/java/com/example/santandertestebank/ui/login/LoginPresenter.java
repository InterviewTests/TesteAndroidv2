package com.example.santandertestebank.ui.login;

import com.example.santandertestebank.R;
import com.example.santandertestebank.model.models.ObjectsLogin;
import com.example.santandertestebank.model.repository.LoginRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View view;

    private final String PASSWORD_REGEX = "^((?=.[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!*?])(?=\\S+$).{3,})$";
    private final String USERNAME_CPF_REGEX = "^([0-9]{3}\\.?){3}-?[0-9]{2}$";
    private final String USERNAME_EMAIL = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}\\@[a-zA-Z0-9][a-zA-Z0-9\\-]" +
            "{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+";


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

    @Override
    public void validateLogin(String username) throws Exception {
        if (!(username.matches (USERNAME_EMAIL) || username.matches (USERNAME_CPF_REGEX))) {
            throw new Exception (view.getContext ().getString (R.string.type_valid_username));
        }
    }

    @Override
    public void validatePassword(String password) throws Exception {
        if (!password.matches (PASSWORD_REGEX)) {
            throw new Exception (view.getContext ().getString (R.string.password_validation_message));
        }
    }
}

//    @Override
//    public void validateCpfLogin(String username)  {
//        if (!username.matches (USERNAME_CPF_REGEX)) {
//            view.getContext ().getString (R.string.type_valid_username);
//        }
//    }
//
//    @Override
//    public void validateEmailLogin(String username) {
//        if (!Patterns.EMAIL_ADDRESS.matcher (username).matches ()) {
//            view.getContext ().getString (R.string.type_valid_username);
//        }
//    }