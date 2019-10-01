package com.riso.zup.bank.loginScreen;

import com.riso.zup.bank.R;
import com.riso.zup.bank.helpers.Validator;

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
        }

    }
}
