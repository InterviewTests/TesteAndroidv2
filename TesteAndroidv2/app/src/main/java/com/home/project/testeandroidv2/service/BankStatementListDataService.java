package com.home.project.testeandroidv2.service;

import com.home.project.testeandroidv2.model.BankStatementResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BankStatementListDataService {

    @GET("statements/{userId}")
    Call<BankStatementResponse> getBankStatementList(@Path(value = "userId") int userId);
}
