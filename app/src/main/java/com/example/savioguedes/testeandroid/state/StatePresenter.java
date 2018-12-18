package com.example.savioguedes.testeandroid.state;

import android.content.Context;
import android.util.Log;

import com.example.savioguedes.testeandroid.model.ResponseStates;
import com.example.savioguedes.testeandroid.service.Api;
import com.example.savioguedes.testeandroid.service.Manager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatePresenter implements StateContract.Presenter {

    private StateContract.View view;
    private Context context;

    StatePresenter(StateContract.View view, Context context){

        this.view = view;
        this.context = context;

        view.initView();
    }

    @Override
    public void getStatementsExecute(String id) {

        Api api = Manager.serviceGenerator().create(Api.class);
        Call<ResponseStates> call = api.getStatements(id);

        call.enqueue(new Callback<ResponseStates>() {

            @Override
            public void onResponse(Call<ResponseStates> call, Response<ResponseStates> response) {

                if (!response.isSuccessful()){

                    view.onErroRequest();
                    Log.i("RESPOSTA_REQUEST_STATE", "Erro: " + response + " - Code: " + response.code());
                }
                else{

                    if(response.body() != null){

                        view.fillList(response.body().getStatementList());

                        Log.i("RESPOSTA_REQUEST_STATE", "Sucesso: " + response.body().getStatementList());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseStates> call, Throwable t) {

                view.onErroRequest();
                Log.i("RESPOSTA_REQUEST_STATE", "Falha: " +t.getMessage());
            }
        });
    }
}
