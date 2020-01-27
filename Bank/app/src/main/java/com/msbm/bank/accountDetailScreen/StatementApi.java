package com.msbm.bank.accountDetailScreen;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface StatementApi {

    @GET("statements/{userId}")
    Call<StatementResponse> fetchStatements(@Path("userId") String userId);
}
