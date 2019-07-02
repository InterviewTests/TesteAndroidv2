package com.example.santanderapplication.ui.login;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BankLoginPresenter implements BankLoginContract.Presenter {

    private BankLoginContract.View view;
    private Pattern pattern;
    private Matcher matcher;


    public BankLoginPresenter (BankLoginContract.View view){
        this.view=view;
    }


    @Override
    public void validateLogin(String user, String password) {
        if(user.isEmpty()){
            view.showMessage( "Digite o usuario" );
            return;
        }

        if(password.isEmpty()){
            view.showMessage( "Digite a senha" );
            return;
        }
    }

    @Override
    public boolean validatePassword(String password) {

        final String PASSWORD_PATTERN = "^(?=.*[0-9])" +
                    "(?=.*[a-z])" +
                    "(?=.*[A-Z])" +
                    "(?=.*[@#$%^&+=])" +
                    ".{4,}$";
            pattern = Pattern.compile( PASSWORD_PATTERN );
            matcher = pattern.matcher( password );
            if(password.matches( PASSWORD_PATTERN )){
                return true;
            }else{
                view.showMessage( "senha invalida" );
            }
            return matcher.matches();
    }

    @Override
    public boolean validateUser(String user) {

        final String USER_CPF_PATTERN = "[0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}\\-?[0-9]{2}";
        String regex = "^(.+)@(.+)$|[0-9]";
        pattern = Pattern.compile( USER_CPF_PATTERN );
        matcher = pattern.matcher( user );
        if (user.matches( regex )||user.matches( USER_CPF_PATTERN )){
            return true;

        }else {
            view.showMessage( "usuário invalido" );
        }

        return user.matches( regex ) || user.matches( USER_CPF_PATTERN );

    }
    @Override
    public void validateIApi() {

    }

}
