package com.br.projetotestesantanter.refactor.statementScreen;

import com.br.projetotestesantanter.api.Endpoint;
import com.br.projetotestesantanter.api.RetrofitConfiguration;
import com.br.projetotestesantanter.refactor.loginScreen.LoginModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

interface StatementIntaractorInput {

    void informationLogin(LoginModel.Login login);
}

public class StatementIntaractor implements  StatementIntaractorInput{

    public StatementPresenterInput output;

    @Override
    public void informationLogin(LoginModel.Login login) {

        RetrofitConfiguration.Companion.getRetrofitInstance();
        Endpoint endpoint = RetrofitConfiguration.Companion.getRetrofitInstance().create(Endpoint.class);

        endpoint.getStatements(login.getLoginResponse().getUserId()).enqueue(new Callback<StatementModel.ListStatemt>() {
            @Override
            public void onResponse(Call<StatementModel.ListStatemt> call, Response<StatementModel.ListStatemt> response) {

                output.hideProgressBar();
                output.responselistStatemntMetaData(response.body());

            }

            @Override
            public void onFailure(Call<StatementModel.ListStatemt> call, Throwable t) {
                output.hideProgressBar();

            }
        });
    }

}
