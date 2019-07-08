package ssilvalucas.testeandroidv2.screen.login;

import android.util.Log;

import java.lang.ref.WeakReference;

import ssilvalucas.testeandroidv2.data.model.LoginResponse;

interface LoginPresenterInput{
    void throwError(String errorMessage);
    void onSuccessfulLogin(LoginResponse response);
}

public class LoginPresenter implements LoginPresenterInput{

    public WeakReference<LoginActivityInput> output;

    @Override
    public void throwError(String errorMessage) {
        output.get().showErrorMessage(errorMessage);
    }

    @Override
    public void onSuccessfulLogin(LoginResponse response) {
        output.get().onSuccessfulLogin(response.getUserAccount());
        Log.i("TESTE ", response.getUserAccount().getAgency() + " - " + response.getUserAccount().getBalance() + " - " + response.getUserAccount().getBankAccount() + " - " + response.getUserAccount().getName() + " - " + response.getUserAccount().getUserId());
    }
}
