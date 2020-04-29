package com.br.web.glix.interviewgiovanipaleologo.loginScreen;

import com.br.web.glix.interviewgiovanipaleologo.models.UserAccount;

import java.lang.ref.WeakReference;

interface LoginScreenPresenterInput {
    void presentLoginScreenData(LoginScreenResponse loginScreenResponse);

    void savePreferences(String userLogin);
    void setUserAccountData(UserAccount userAccount);

    boolean verificarConexao();
    void connectionRefused();
    void emptyUsername();
    void emptyPassword();
    void invalidUsername();
    void invalidPassword();
    void invalidLogin();
    void clearPassword();
}


public class LoginScreenPresenter implements LoginScreenPresenterInput {
    public WeakReference<LoginScreenActivityInput> output;


    @Override
    public void presentLoginScreenData(LoginScreenResponse loginScreenResponse) {
        if (loginScreenResponse != null) {
            output.get().setUserAccountData(loginScreenResponse.userAccount);
            output.get().savePreferences(loginScreenResponse.userAccount.getUserId());
            output.get().nextScreen();
        }
    }

    @Override
    public void savePreferences(String userLogin){
        output.get().savePreferences(userLogin);
    }

    @Override
    public void setUserAccountData(UserAccount userAccount){
        output.get().setUserAccountData(userAccount);
    }

    @Override
    public void emptyUsername() {
        output.get().emptyUsername();
    }

    @Override
    public void emptyPassword() {
        output.get().emptyPassword();
    }

    @Override
    public void invalidUsername() {
        output.get().invalidUsername();
    }

    @Override
    public void invalidPassword() {
        output.get().invalidPassword();
    }

    @Override
    public void invalidLogin() {
        output.get().invalidLogin();
    }

    @Override
    public void clearPassword(){
        output.get().clearPassword();
    }

    @Override
    public boolean verificarConexao(){
        return output.get().verificarConexao();
    }

    @Override
    public void connectionRefused(){
        output.get().connectionRefused();
    }
}
