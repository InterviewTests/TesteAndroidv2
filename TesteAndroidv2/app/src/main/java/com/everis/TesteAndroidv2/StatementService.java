package com.everis.TesteAndroidv2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface StatementService {

    @GET("api/statements/{idUser}")
    Call<Lancamento> checarExtrato(@Path("idUser") String idUser);
}
