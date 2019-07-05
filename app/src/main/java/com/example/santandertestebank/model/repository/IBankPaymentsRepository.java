package com.example.santandertestebank.model.repository;

import com.example.santandertestebank.model.models.ObjectsStatements;

import retrofit2.Call;

public interface IBankPaymentsRepository {

    Call<ObjectsStatements> bringStatements(long id);
}
