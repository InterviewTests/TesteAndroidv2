package com.example.bankapp.remote;

import com.example.bankapp.model.statementListModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface statementListAPI {

    @GET("statements/idUser")
    Call<statementListModel> getList(@Path("id") long id);

}
