package com.accenture.project.apptesteandroid.remoteData;

import com.accenture.project.apptesteandroid.model.BankStatementResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BankStatementDataService {

    @GET("statements/{userId}")
    Call<BankStatementResponse> getBankStatementList(@Path(value = "userId") int userId);
}
