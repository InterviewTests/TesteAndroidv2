package com.resource.bankapplication.data.repository;

import com.resource.bankapplication.config.BaseCallback;
import com.resource.bankapplication.config.Repository;
import com.resource.bankapplication.data.remote.StatementsService;
import com.resource.bankapplication.data.remote.dto.SpentDto;
import com.resource.bankapplication.domain.Spent;
import com.resource.bankapplication.domain.SpentContract;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatementsRepository extends Repository implements SpentContract.IRepository {
    @Override
    public void listStatements(long idUser, BaseCallback<List<Spent>> onResult) {
        super.data.restApi(StatementsService.class).spentList(idUser)
                .enqueue(new Callback<SpentDto>() {
                    @Override
                    public void onResponse(Call<SpentDto> call, Response<SpentDto> response) {
                        if(response.isSuccessful() && response.body().getError().getCode()==0) {
                            if (response.body().getStatementList().size() < 1){
                                onResult.onUnsuccessful("Nenhum registro Encontrado!");
                                return;
                            }
                            onResult.onSuccessful(response.body().toDomain());
                        } else
                            onResult.onUnsuccessful(response.body().getError().getMessage());

                    }

                    @Override
                    public void onFailure(Call<SpentDto> call, Throwable t) {
                        onResult.onUnsuccessful(t.getMessage());
                    }
                });
    }
}
