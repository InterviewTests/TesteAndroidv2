package com.br.example.fakebank.domains.services;

import com.br.example.fakebank.domains.repositories.CurrencyRepository;
import com.br.example.fakebank.infrastructure.retrofit.RetrofitConfig;
import com.br.example.fakebank.infrastructure.retrofit.entities.ErrorEntity;
import com.br.example.fakebank.infrastructure.retrofit.responses.CurrencyResponse;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrencyService implements CurrencyRepository {
    private RetrofitConfig retrofitConfig;

    public CurrencyService() {
        this.retrofitConfig = new RetrofitConfig();
    }

    @Override
    public Observable<CurrencyResponse> listStatements(Integer userId) {
        return Observable.create(emitter -> {

            Call<CurrencyResponse> currencyResponseCall = retrofitConfig.getCurrencyEndPoint().getListStatements(userId);
            currencyResponseCall.enqueue(new Callback<CurrencyResponse>() {
                @Override
                public void onResponse(Call<CurrencyResponse> call, Response<CurrencyResponse> response)
                {
                    CurrencyResponse currencyResponse;
                    if (response.isSuccessful()) {
                         currencyResponse = response.body();
                        emitter.onNext(currencyResponse);
                    }else {
                        currencyResponse = new CurrencyResponse();
                        ErrorEntity errorEntity = new ErrorEntity();
                        errorEntity.setMessage("Usuário não encontrado");
                        currencyResponse.setError(errorEntity);
                        currencyResponse.setStatementList(null);
                    }
                }

                @Override
                public void onFailure(Call<CurrencyResponse> call, Throwable t) {
                    emitter.onError(t);
                }
            });
        });
    }
}
