package com.gft.testegft.network;

import android.util.Log;

import com.gft.testegft.login.data.LoginResponse;

import javax.inject.Inject;

import io.reactivex.Single;
import retrofit2.Response;

public class ApiRepository {

    private final ApiInterface repoService;

    @Inject
    public ApiRepository(ApiInterface repoService) {
        this.repoService = repoService;
    }

    public Single<LoginResponse> login(String userName, String password) {
        return repoService.login(userName, password);
    }

    public Single<Response> get(String id) {
        return repoService.getStatements(id);
    }
}