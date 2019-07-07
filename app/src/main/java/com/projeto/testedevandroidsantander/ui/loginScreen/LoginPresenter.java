package com.projeto.testedevandroidsantander.ui.loginScreen;

import android.content.Context;

import com.projeto.testedevandroidsantander.model.UsuarioModel;
import com.projeto.testedevandroidsantander.model.UsuarioViewModel;

import java.lang.ref.WeakReference;

interface LoginPresenterInput {
    void presentLoginMetaData(LoginResponse response);
    void visibleProgressBar();
    void hideProgressBar();
    void showMessageLoginError(String message);
    void setLoginSharedPreferences(String login);
    String getMessageLoginError();
    String getMessageCpfError();
    String getMessageSenhaError();
    Context getContext();

}

public class LoginPresenter implements LoginPresenterInput {

    public WeakReference<LoginActivityInput> output;

    @Override
    public void presentLoginMetaData(LoginResponse response) {
        LoginViewModel loginViewModel = new LoginViewModel();

        if (response.usuarioModel.id != null) {
            UsuarioModel um = response.usuarioModel;
            UsuarioViewModel uvm = new UsuarioViewModel();

            uvm.id = um.id;
            uvm.nome = um.nome;
            uvm.conta = um.conta;
            uvm.agencia = um.agencia;
            uvm.saldo = um.saldo;

            loginViewModel.usuarioViewModel = uvm;
            output.get().displayLoginMetaData(loginViewModel);
        }else{
            showMessageLoginError(getMessageLoginError());
        }
    }

    @Override
    public void visibleProgressBar() {
        output.get().showProgressBar();
    }

    @Override
    public void hideProgressBar() {
        output.get().hideProgressBar();
    }

    @Override
    public void showMessageLoginError(String message) {
        output.get().showMessageLoginError(message);
    }

    @Override
    public void setLoginSharedPreferences(String login) {
        output.get().setLoginSharedPreferences(login);
    }

    @Override
    public String getMessageLoginError() {
        return output.get().getMessageLoginError();
    }

    @Override
    public String getMessageCpfError() {
        return output.get().getMessageCpfError();
    }

    @Override
    public String getMessageSenhaError() {
        return output.get().getMessageSenhaError();
    }

    @Override
    public Context getContext() {
        return output.get().getContext();
    }
}
