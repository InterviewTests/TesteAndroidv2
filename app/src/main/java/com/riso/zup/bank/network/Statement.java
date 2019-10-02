package com.riso.zup.bank.network;

import com.riso.zup.bank.models.ResponseStatement;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Statement {

    @GET("statements/{id}")
    Call<ResponseStatement> getExtract(@Path("id") int id);
}
