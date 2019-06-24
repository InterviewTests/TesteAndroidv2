package com.example.bankapp.data.remote;

import com.example.bankapp.data.remote.model.dashboard.StatementListModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface statementListAPI {

    @GET("statements/{idUser}")
    Call<StatementListModel> getList(@Path("idUser") long id);

}
