package com.br.projetotestesantanter.refactor.loginScreen;

interface LoginInteractorInput {
    void fetchHomeMetaData(LoginModel.LoginRequest request);
}
public class LoginInteractor implements LoginInteractorInput{


    public LoginPresenterInput output;

    @Override
    public void fetchHomeMetaData(LoginModel.LoginRequest request) {

    }
}
