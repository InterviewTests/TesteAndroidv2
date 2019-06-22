package com.example.projectsantander.transacao;

import com.example.projectsantander.services.RetrofitApi;
import com.example.projectsantander.services.TransacaoResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransacaoModelImpl implements TransacaoContract.TransacaoModel {

    private TransacaoContract.TransacaoPresenter presenter;


    public TransacaoModelImpl(TransacaoPresenterImpl transacaoPresenter) {
        this.presenter = transacaoPresenter;
    }

    @Override
    public void getListagem(int userId) {

        new RetrofitApi().getService().transacoes(userId).enqueue(new Callback<TransacaoResponse>() {
            @Override
            public void onResponse(Call<TransacaoResponse> call, Response<TransacaoResponse> response) {
                TransacaoResponse resp = response.body();
                if(resp.getError() == null || resp.getError().getCode() == 0){
                    presenter.preencheListagem(resp.getStatementList());
                }
                else{
                    presenter.erroListagem(resp.getError().getMessage());
                }
            }

            @Override
            public void onFailure(Call<TransacaoResponse> call, Throwable t) {
                presenter.erroServidor();
            }
        });

    }
}
