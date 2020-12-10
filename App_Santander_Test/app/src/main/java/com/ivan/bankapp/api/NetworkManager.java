package com.ivan.bankapp.api;

import com.ivan.bankapp.model.StatementList;
import com.ivan.bankapp.model.User;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkManager {
    private static final NetworkManager instance = new NetworkManager();
    private final Api api;

    private final BreedApiService breedApiService;

    private NetworkManager() {
        Retrofit retrofitCore =
                new Retrofit
                        .Builder()
                        .baseUrl("https://bank-app-test.herokuapp.com/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(new OkHttpClient())
                        .build();

        api = retrofitCore.create(Api.class);

        breedApiService = new BreedApiService();
    }

    public static NetworkManager getInstance() {
        return instance;
    }

    public void getStatements(NetworkResponseListener<StatementList> listener) {
        breedApiService.getStatement(listener, api);
    }

    public void login(NetworkResponseListener<User> listener) {
        breedApiService.login(listener, api);
    }
}
