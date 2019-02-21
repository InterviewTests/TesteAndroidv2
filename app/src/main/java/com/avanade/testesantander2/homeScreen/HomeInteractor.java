package com.avanade.testesantander2.homeScreen;


import android.util.Log;

import com.avanade.testesantander2.APIRetrofitService;
import com.avanade.testesantander2.CurrencyResponse;
import com.avanade.testesantander2.Erro;
import com.avanade.testesantander2.UserAccount;
import com.avanade.testesantander2.util.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

interface HomeInteractorInput {
    void getData(UserAccount userAccount);
}

public class HomeInteractor implements HomeInteractorInput {

    private static String TAG = HomeInteractor.class.getSimpleName();

    HomePresenterInput output;
    HomeRequest homeRequest;
    HomeViewModel homeViewModel;
    Erro erro;

    @Override
    public void getData(UserAccount userAccount) {

        homeViewModel = new HomeViewModel();
        homeViewModel.userAccount = userAccount;

        homeRequest = new HomeRequest();
        homeRequest.idUser = userAccount.getUserId();

        // envia a conta de usuario para exibir na janela
        showUser();

        APIRetrofitService apiRetrofit = ApiClient.getClient(ApiClient.BASE_URL).create(APIRetrofitService.class);
        Call<CurrencyResponse> call = apiRetrofit.getCurrency(homeRequest.idUser);

        call.enqueue(new Callback<CurrencyResponse>() {

            @Override
            public void onResponse(Call<CurrencyResponse> call, Response<CurrencyResponse> response) {
                //Log.d(TAG, "-------------------- RESPONSE: " + response.body().toString());

                HomeResponse homeResponse = new HomeResponse();
                homeResponse.apiCallCurrencyResponse = response.body();

                if (response.isSuccessful()) {
                    Log.d(TAG, "-------------------- RESPONSE SUCESSFUL");

                    // int default é ZERO -> logo não retornou erros
                    if (response.body().getError().getCode() == 0) {

                        homeViewModel = new HomeViewModel();
                        homeViewModel.userAccount = userAccount;
                        homeViewModel.currencyAccount = homeResponse.apiCallCurrencyResponse.getStatementList();

                        showDados();
                    }

                } else {
                    Log.d(TAG, "Resnponse Error: " + response.errorBody().toString());

                    erro = homeResponse.apiCallCurrencyResponse.getError();
                    output.apresentarErro(erro.toString());
                }
            }

            @Override
            public void onFailure(Call<CurrencyResponse> call, Throwable t) {
                // Log error here since homeRequest failed
                Log.e(TAG, "RESPONSE FAILURE - Erro: " + t.toString());
                output.apresentarErro(t.toString());

            }
        });
    }

    public void showUser(){
        output.apresentarUsuario(homeViewModel.userAccount);
    }

    public void showDados(){
        output.apresentarDados(homeViewModel);
    }

    public void showErro() {
        if (erro != null)
            output.apresentarErro(erro.toString());
    }
}
