package com.example.projectsantander.login;

import com.example.projectsantander.services.LoginResponse;

public class LoginPresenterImpl implements LoginContract.LoginPresenter {

    private LoginContract.LoginView view;
    private LoginContract.LoginModel model;


    public LoginPresenterImpl(LoginContract.LoginView view){
        this.view = view;
        this.model = new LoginModelImpl(this);
    }

    @Override
    public void realizaLogin(String usuario, String senha){
       model.realizaLogin(usuario, senha);
    }

    @Override
    public void usuarioNaoInformado() {
        view.exibeMensagem("Informe o usuário");
        view.focarUsuario();
    }

    @Override
    public void senhaNaoInformada() {
        view.exibeMensagem("Informe a senha");
        view.focarSenha();
    }

    @Override
    public void senhaInvalida() {
        view.exibeMensagem("Senha Invalida. Use um caractere especial\n" +
                " um alpha numerico \n e um caractere maiusculo.");
    }

    @Override
    public void userInvalido() {
        view.exibeMensagem("Usuario Invalido!");
    }


    @Override
    public void loadingRequisicao() {
        view.exibeLoading("Entrando...");
    }

    @Override
    public void fechaLoading() {
        view.fechaLoading();
    }

    @Override
    public void loginCompleto(Login login) {
        view.chamaTelaTransacoes(login);
    }

    @Override
    public void loginIncorreto(String message) {
        view.limparCampos();
        view.exibeMensagem(message);
    }



    @Override
    public void erroServidor() {
        view.exibeMensagem("Problema ao conectar no servidor. Verifique a internet.");
    }
}
