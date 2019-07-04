package com.example.santanderapplication.ui.login;

import com.example.santanderapplication.data.model.LoginRequestModel;
import com.example.santanderapplication.data.model.LoginResponseModel;
import com.example.santanderapplication.service.IApiLogin;
import com.example.santanderapplication.service.ServiceRetrofit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BankLoginPresenter implements BankLoginContract.Presenter {

    private BankLoginContract.View view;
    private Pattern pattern;
    private Matcher matcher;


    public BankLoginPresenter(BankLoginContract.View view) {
        this.view = view;
    }


    @Override
    public void validateLogin(String user, String password) {
        view.showProgress( true );
        if (user.isEmpty()) {
            view.showMessage( "Digite o usuario" );
            view.showProgress( false );
            return;
        }

        if (password.isEmpty()) {
            view.showMessage( "Digite a senha" );
            view.showProgress( false );
            return;
        }
        view.showProgress( false );
    }

    @Override
    public boolean validatePassword(String password) {
        view.showProgress( true );
        final String PASSWORD_PATTERN = "^(?=.*[0-9])" +
                "(?=.*[a-z])" +
                "(?=.*[A-Z])" +
                "(?=.*[@#$%^&+=])" +
                ".{4,}$";
        pattern = Pattern.compile( PASSWORD_PATTERN );
        matcher = pattern.matcher( password );
        if (password.matches( PASSWORD_PATTERN )) {
            view.showProgress( false );
            return true;
        } else {
            view.showMessage( "senha invalida" );
            view.showProgress( false );
        }
        view.showProgress( false );
        return matcher.matches();
    }

    @Override
    public boolean validateUser(String user) {

        final String USER_CPF_REGEX = "[0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}\\-?[0-9]{2}";
        String regex = "^(.+)@(.+)$|[0-9]";
        pattern = Pattern.compile( USER_CPF_REGEX );
        matcher = pattern.matcher( user );
        if (user.matches( regex ) || user.matches( USER_CPF_REGEX )) {
            return true;

        } else {
            view.showMessage( "usuário invalido" );
        }

        return user.matches( regex ) || user.matches( USER_CPF_REGEX );

    }

    @Override
    public void eatinglogin(String user, String password) {
        view.showProgress( true );
        IApiLogin apiLogin = ServiceRetrofit.createService( IApiLogin.class );
        Call<LoginResponseModel> loginResponseModelCall = apiLogin.postLoginApi( new LoginRequestModel(
                user, password
        ) );
        loginResponseModelCall.enqueue( new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(Call<LoginResponseModel> call,
                                   Response<LoginResponseModel> response) {
                if (response.isSuccessful()) {
                    LoginResponseModel loginResponseModel = response.body();
                    view.showActivity( loginResponseModel );
                    view.showProgress( false );
                }
                view.showProgress( false );
            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                try {
                    view.showMessage( "erro" );
                    view.showProgress( false );
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } );
    }
}
