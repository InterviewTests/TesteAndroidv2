package com.avanade.testesantander2.loginScreen;

import com.avanade.testesantander2.UserAccount;

import java.lang.ref.WeakReference;
import java.util.List;

interface LoginPresenterInput {
    void setUsuario(String login, String senha);

    void setReponseOK(UserAccount apiPostLoginResponse);

    void setErros(List<String> erros);
}

public class LoginPresenter implements LoginPresenterInput {
    public static String TAG = LoginPresenter.class.getSimpleName();

    public WeakReference<LoginActivityInput> output;


    @Override
    public void setUsuario(String login, String senha) {
        output.get().setUsuario(login, senha);
    }

    @Override
    public void setReponseOK(UserAccount userAccount) {
        LoginViewModel loginViewModel = new LoginViewModel();
        loginViewModel.userAccount = userAccount;

        // Solicita abrir HomeActivity
        output.get().openHomeScreen(userAccount);
    }

    @Override
    public void setErros(List<String> erros) {
        output.get().showErros(erros);
    }
}

