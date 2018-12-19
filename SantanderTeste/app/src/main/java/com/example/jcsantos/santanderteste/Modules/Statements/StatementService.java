package com.example.jcsantos.santanderteste.Modules.Statements;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface StatementService {
    @Headers({"Content-Type: application/x-www-form-urlencoded"})
    @GET("statements/{userId}")
    Call<StatementModel> statementsService(@Path("userId") int userId);
}
