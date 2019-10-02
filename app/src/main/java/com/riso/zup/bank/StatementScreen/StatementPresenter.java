package com.riso.zup.bank.StatementScreen;

import com.riso.zup.bank.R;
import com.riso.zup.bank.models.ResponseStatement;
import com.riso.zup.bank.network.ApiConfig;
import com.riso.zup.bank.network.Statement;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatementPresenter implements StatementInteractor.Presenter{

    private StatementInteractor.View view;

    public StatementPresenter(StatementInteractor.View view){
        this.view = view;
    }

    @Override
    public void getListStatement(int id) {

        //Call conection to DataBase

        Statement statementClient = ApiConfig.create(Statement.class);
        Call<ResponseStatement> call = statementClient.getExtract(id);
        call.enqueue(new Callback<ResponseStatement>() {
            @Override
            public void onResponse(Call<ResponseStatement> call, Response<ResponseStatement> response) {
                ResponseStatement responseStatement = response.body();

                if(response.isSuccessful()){
                    view.receiveListStatement(responseStatement.getStatementList());
                }
                else{
                    view.loadError(R.string.error_load_statement);
                }
            }

            @Override
            public void onFailure(Call<ResponseStatement> call, Throwable t) {
                view.loadError(R.string.error_load_statement);
            }
        });

    }
}
