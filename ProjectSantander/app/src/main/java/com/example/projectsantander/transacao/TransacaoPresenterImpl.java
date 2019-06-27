package com.example.projectsantander.transacao;

import com.example.projectsantander.login.Login;
import com.example.projectsantander.services.RetrofitApi;
import com.example.projectsantander.services.TransacaoResponse;

import java.text.NumberFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransacaoPresenterImpl implements TransacaoContract.TransacaoPresenter{

    private TransacaoContract.TransacaoModel model;
    private TransacaoContract.TransacaoView view;

    public TransacaoPresenterImpl(TransacaoContract.TransacaoView view){
        this.view = view;
        this.model = new TransacaoModelImpl(this);
    }

    @Override
    public void setDados(Login dados) {
        view.preencherNome(dados.getName());
        view.preencherDadosBancarios(dados.getAgency()+" / "+dados.getBankAccount());
        view.preencherSaldo(NumberFormat.getCurrencyInstance().format(dados.getBalance()));
        view.exibeLoading("Carregando transações...");
        model.getListagem(dados.getUserId());
    }

    @Override
    public void preencheListagem(List<Transacao> lista) {
        view.fechaLoading();
        view.preencheLista(lista);
    }

    @Override
    public void erroServidor() {
        view.fechaLoading();
        view.exibeMensagem("Erro ao conectar no servidor. Verifique a internet");
    }

    @Override
    public void erroListagem(String message) {
        view.fechaLoading();
        view.exibeMensagem(message);
    }

    @Override
    public void sair() {
        view.chamarLogin();
    }


}
