package com.example.santandertestebank.model.repository;

import com.example.santandertestebank.model.models.ObjectsLogin;

import retrofit2.Call;

public interface ILoginRepository {

    Call<ObjectsLogin> loginUser(String username, String password);
}
