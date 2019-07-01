package br.com.fernandodutra.testeandroidv2.activities.login;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import br.com.fernandodutra.testeandroidv2.models.UserAccountWorker;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 26/06/2019
 * Time: 21:14
 * TesteAndroidv2_CleanCode
 */

interface LoginPresenterInput {
    void presentLoginMetaData(LoginResponse response);
}

public class LoginPresenter implements LoginPresenterInput {

    public WeakReference<LoginActivityInput> loginActivityInputWeakReference;
    //
    public static String TAG = LoginPresenter.class.getSimpleName();

    @Override
    public void presentLoginMetaData(LoginResponse response) {

        LoginViewModel loginViewModel = new LoginViewModel();

        loginViewModel.userAccountWorker = new UserAccountWorker();

        if (response.errorMessage.size() > 0) {
            loginViewModel.userAccountWorker = new UserAccountWorker();
            loginViewModel.errorMessage = response.errorMessage;
        } else {
            loginViewModel.userAccountWorker = response.userAccountWorker;
            loginViewModel.errorMessage = new ArrayList<>();
        }

        loginActivityInputWeakReference.get().displayLoginMetaData(loginViewModel);
    }
}
