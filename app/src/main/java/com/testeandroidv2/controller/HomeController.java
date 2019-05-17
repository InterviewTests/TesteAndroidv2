package com.testeandroidv2.controller;

import com.orhanobut.hawk.Hawk;
import com.testeandroidv2.App;
import com.testeandroidv2.contract.presenter.HomePresenter;
import com.testeandroidv2.contract.view.HomeView;
import com.testeandroidv2.repository.LoginService;
import com.testeandroidv2.repository.RetrofitInstance;
import com.testeandroidv2.repository.response.Error;
import com.testeandroidv2.repository.response.StatementResponse;
import com.testeandroidv2.repository.response.UserAccount;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeController implements HomePresenter {

    private HomeView homeView;

    public HomeController(HomeView homeView){
        this.homeView = homeView;
    }

    @Override
    public void loadHeader() {

        homeView.showProgress();

        UserAccount userAccount = App.userAccount;

        homeView.setHeader(userAccount);
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
                        homeView.setStatementList(statementResponse.getStatementList());
                        homeView.dismissProgress();

                    } else {

                        App.error = response.body().getError();

                        homeView.dismissProgress();
                        homeView.showErrorActivity();
                    }

                } else {

                    StatementResponse statementResponse = response.body();
                    App.error = statementResponse.getError();

                    homeView.dismissProgress();
                    homeView.showErrorActivity();
                }
            }

            @Override
            public void onFailure(Call<StatementResponse> call, Throwable t) {
                Error error = new Error();
                error.setCode("Error");
                error.setMessage(t.getMessage());
                homeView.showErrorActivity();
            }
        });
    }
}
