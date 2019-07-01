package com.example.androidcodingtest.statements;

import com.example.androidcodingtest.connections.ApiClient;
import com.example.androidcodingtest.connections.StatementClient;
import com.example.androidcodingtest.R;
import com.example.androidcodingtest.models.StatementsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatementPresenter implements StatementInteractor.Presenter {

    StatementInteractor.View view;
    public StatementPresenter(StatementInteractor.View statementActivity) {
        this.view = statementActivity;
    }

    @Override
    public void getStatements(int userId) {

        StatementClient statementClient = ApiClient.create(StatementClient.class);
        Call<StatementsResponse> call = statementClient.getStatments(userId);
        call.enqueue(new Callback<StatementsResponse>() {
            @Override
            public void onResponse(Call<StatementsResponse> call, Response<StatementsResponse> response) {
                StatementsResponse statementsResponse = response.body();

                if(response.isSuccessful()){
                    view.generateStatementList(statementsResponse.getStatementList());
                }
                else{
                    view.showMessage(R.string.fail_to_load_statements);
                }
            }

            @Override
            public void onFailure(Call<StatementsResponse> call, Throwable t) {
                view.showMessage(R.string.fail_to_load_statements);
            }
        });
    }
}
