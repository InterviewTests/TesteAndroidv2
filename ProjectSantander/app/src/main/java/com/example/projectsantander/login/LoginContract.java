package com.example.projectsantander.login;

import com.example.projectsantander.services.LoginResponse;

public class LoginContract {

    interface LoginPresenter{

        void realizaLogin(String usuario, String senha);

        void usuarioNaoInformado();

        void senhaNaoInformada();

        void senhaInvalida();

        void userInvalido();


        void loadingRequisicao();

        void fechaLoading();

        void loginCompleto(Login login);

        void loginIncorreto(String message);



        void erroServidor();
    }

    interface LoginModel{

        void realizaLogin(String usuario, String senha);
    }

    interface LoginView{

        void exibeMensagem(String msg);

        void exibeLoading(String s);

        void fechaLoading();

        void focarUsuario();

        void focarSenha();

        void chamaTelaTransacoes(Login body);

        void limparCampos();
    }
}
