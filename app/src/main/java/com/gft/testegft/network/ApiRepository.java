package com.gft.testegft.network;

import com.gft.testegft.login.data.LoginResponse;
import com.gft.testegft.statements.data.StatementResponse;

import javax.inject.Inject;

import io.reactivex.Single;

public class ApiRepository {

    private final ApiInterface repoService;

    @Inject
    public ApiRepository(ApiInterface repoService) {
        this.repoService = repoService;
    }

    public Single<LoginResponse> login(String userName, String password) {
        return repoService.login(userName, password);
    }

    public Single<StatementResponse> get(String id) {
        return repoService.getStatements(id);
    }
}