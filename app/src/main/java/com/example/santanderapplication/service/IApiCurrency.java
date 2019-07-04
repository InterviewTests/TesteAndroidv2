package com.example.santanderapplication.service;


import com.example.santanderapplication.data.model.StatementsResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IApiCurrency {
    @GET("statements/{idUser}")
    Call<StatementsResponseModel> getStatementsApi(@Path("idUser") int Id);
}
