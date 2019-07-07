package ssilvalucas.testeandroidv2.screen.login;

import ssilvalucas.testeandroidv2.data.model.LoginResponse;

interface LoginPresenterInput{
    void isEmptyUsername();
    void isEmptyPassword();
    void invalidUsername();
    void invalidPassword();
    void showErrorMessage(String errorMessage);
    void onSuccessfulLogin(LoginResponse response);
    void setLastLoggedUser();
}

public class LoginPresenter {
}
