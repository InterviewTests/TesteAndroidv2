package com.example.santanderapplication.ui.login;

public class BankLoginPresenter implements BankLoginContract.Presenter {

    private BankLoginContract.View view;

    public BankLoginPresenter (BankLoginContract.View view){
        this.view=view;
    }


    @Override
    public void validateUser(String user, String password) {
        if(user.isEmpty()){
            view.showMessage( "Digite o usuario" );
            return;
        }
    }
}
