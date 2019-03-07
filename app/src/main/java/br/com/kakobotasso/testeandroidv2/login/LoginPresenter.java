package br.com.kakobotasso.testeandroidv2.login;

import java.lang.ref.WeakReference;

import br.com.kakobotasso.testeandroidv2.R;

interface LoginPresenterInput {
    void presentLoginData(LoginResponse response);

    void presentInvalidRequestData();
}

public class LoginPresenter implements LoginPresenterInput {
    public WeakReference<LoginActivityInput> output;

    @Override
    public void presentLoginData(LoginResponse response) {
        if (response.hasErrors()) {
            output.get().displayLoginError(response.getErrorMessage());
        } else {
            output.get().displayCurrency(response);
        }
    }

    @Override
    public void presentInvalidRequestData() {
        LoginActivity activity = (LoginActivity) output.get();
        String msg = activity.getResources().getString(R.string.invalid_data);
        output.get().displayLoginError(msg);
    }
}
