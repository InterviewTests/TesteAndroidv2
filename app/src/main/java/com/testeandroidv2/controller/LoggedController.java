package com.testeandroidv2.controller;

import com.orhanobut.hawk.Hawk;
import com.testeandroidv2.App;
import com.testeandroidv2.contract.presenter.LoggedInPresenter;
import com.testeandroidv2.contract.view.LoggedView;
import com.testeandroidv2.repository.LoginService;
import com.testeandroidv2.repository.RetrofitInstance;
import com.testeandroidv2.repository.response.Error;
import com.testeandroidv2.repository.response.StatementResponse;
import com.testeandroidv2.repository.response.UserAccount;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoggedController implements LoggedInPresenter {

    private LoggedView loggedView;

    public LoggedController(LoggedView loggedView){
        this.loggedView = loggedView;
    }

    @Override
    public void loadHeader() {

        loggedView.showProgress();

        UserAccount userAccount = App.userAccount;

        loggedView.setHeader(userAccount);
    }

    @Override
    public void loadStatements() {

        LoginService loggedInService = RetrofitInstance.createService(LoginService.class, "user", "secretpassword");
        Call<StatementResponse> call = loggedInService.getStatements(Hawk.get("idUser"));

        call.enqueue(new Callback<StatementResponse>() {
            @Override
            public void onResponse(Call<StatementResponse> call, Response<StatementResponse> response) {
                if (response.isSuccessful()) {

                    if (response.body() != null) {
                        response.body().getError();
                    }

                    Error error = response.body().getError();

                    if(error.getCode() == null) {

                        StatementResponse statementResponse = response.body();
                        loggedView.setStatementList(statementResponse.getStatementList());
                        loggedView.dismissProgress();

                    } else {

                        App.error = response.body().getError();

                        loggedView.dismissProgress();
                        loggedView.showErrorActivity();
                    }

                } else {

                    StatementResponse statementResponse = response.body();
                    App.error = statementResponse.getError();

                    loggedView.dismissProgress();
                    loggedView.showErrorActivity();
                }
            }

            @Override
            public void onFailure(Call<StatementResponse> call, Throwable t) {
                Error error = new Error();
                error.setCode("Error");
                error.setMessage(t.getMessage());
                loggedView.showErrorActivity();
            }
        });
    }
}
