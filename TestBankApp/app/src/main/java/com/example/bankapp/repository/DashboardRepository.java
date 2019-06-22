package com.example.bankapp.repository;

import com.example.bankapp.domain.DashboardContract;
import com.example.bankapp.helper.BaseCallback;
import com.example.bankapp.model.dashboard.StatementListModel;
import com.example.bankapp.remote.BuildApi;
import com.example.bankapp.remote.statementListAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardRepository extends BuildApi implements DashboardContract.IRepository {
    @Override
    public void getList(long id, final BaseCallback<StatementListModel> result) {
        super.getBuild(statementListAPI.class).getList(id).enqueue(new Callback<StatementListModel>() {
            @Override
            public void onResponse(Call<StatementListModel> call, Response<StatementListModel> response) {
                if(response.body().getError().getMessage()!=null){
                    result.onUnsuccessful(response.body().getError().getMessage());
                    return;
                }

                result.onSuccessful(response.body());
            }

            @Override
            public void onFailure(Call<StatementListModel> call, Throwable t) {
                result.onUnsuccessful("Verifique suas conexões com a internet");
            }
        });
    }
}
