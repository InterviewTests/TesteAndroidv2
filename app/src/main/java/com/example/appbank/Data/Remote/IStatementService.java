package com.example.appbank.data.remote;

import com.example.appbank.data.remote.model.Statement;
import com.example.appbank.data.remote.model.StatementResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IStatementService {
    @GET("statements/{Id}")
    Call<StatementResponse> getStatement(@Path("Id") int Id);
}

