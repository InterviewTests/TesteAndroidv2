package com.example.santanderapplication.ui.transactions;

import com.example.santanderapplication.data.model.StatementsResponseModel;
import com.example.santanderapplication.service.IApiCurrency;
import com.example.santanderapplication.service.ServiceRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BankCurrencyPresenter implements BankCurrencyContract.StatementPresenter {

    private BankCurrencyContract.StatementView view;

    public BankCurrencyPresenter(BankCurrencyContract.StatementView view) {
        this.view = view;
    }

    @Override
    public void eatingCurrency(int id) {

        IApiCurrency apiCurrency = ServiceRetrofit.createService( IApiCurrency.class );
        Call<StatementsResponseModel> statementsResponseModelCall = apiCurrency.getStatementsApi( 1 );
        statementsResponseModelCall.enqueue( new Callback<StatementsResponseModel>() {
            @Override
            public void onResponse(Call<StatementsResponseModel> call,
                                   Response<StatementsResponseModel> response) {
                if (response.isSuccessful()) {
                    StatementsResponseModel statementsResponseModel = response.body();
                    view.loadList( statementsResponseModel );
                }
            }

            @Override
            public void onFailure(Call<StatementsResponseModel> call, Throwable t) {
                try {
                    view.showMessage( "erro" );
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } );

    }
}
