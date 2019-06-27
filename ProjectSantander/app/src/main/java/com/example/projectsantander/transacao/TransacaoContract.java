package com.example.projectsantander.transacao;

import com.example.projectsantander.login.Login;

import java.util.List;

public class TransacaoContract {

    interface TransacaoModel{

        void getListagem(int userId);
    }

    interface TransacaoPresenter{


        void setDados(Login dados);

        void preencheListagem(List<Transacao> lista);

        void erroServidor();

        void erroListagem(String message);

        void sair();
    }

    interface TransacaoView{

        void preencherNome(String nome);

        void preencherDadosBancarios(String dados);

        void preencherSaldo(String saldo);

        void exibeMensagem(String msg);

        void exibeLoading(String msg);

        void fechaLoading();

        void preencheLista(List<Transacao> lista);

        void chamarLogin();
    }
}
