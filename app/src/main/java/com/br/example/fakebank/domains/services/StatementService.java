package com.br.example.fakebank.domains.services;

import com.br.example.fakebank.domains.repositories.StatementRepository;
import com.br.example.fakebank.infrastructure.retrofit.RetrofitConfig;
import com.br.example.fakebank.infrastructure.retrofit.entities.ErrorEntity;
import com.br.example.fakebank.infrastructure.retrofit.responses.StatementResponse;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatementService implements StatementRepository {
    private RetrofitConfig retrofitConfig;

    public StatementService() {
        this.retrofitConfig = new RetrofitConfig();
    }

    @Override
    public Observable<StatementResponse> listStatements(Integer userId) {
        return Observable.create(emitter -> {

            Call<StatementResponse> currencyResponseCall = retrofitConfig.getCurrencyEndPoint().getListStatements(userId);
            currencyResponseCall.enqueue(new Callback<StatementResponse>() {
                @Override
                public void onResponse(Call<StatementResponse> call, Response<StatementResponse> response)
                {
                    StatementResponse statementResponse;
                    if (response.isSuccessful()) {
                         statementResponse = response.body();
                        emitter.onNext(statementResponse);
                    }else {
                        statementResponse = new StatementResponse();
                        ErrorEntity errorEntity = new ErrorEntity();
                        errorEntity.setMessage("Usuário não encontrado");
                        statementResponse.setError(errorEntity);
                        statementResponse.setStatementList(null);
                    }
                }

                @Override
                public void onFailure(Call<StatementResponse> call, Throwable t) {
                    emitter.onError(t);
                }
            });
        });
    }
}
