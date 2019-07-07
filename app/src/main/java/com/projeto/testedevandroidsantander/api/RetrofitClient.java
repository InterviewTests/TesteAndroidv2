package com.projeto.testedevandroidsantander.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "https://bank-app-test.herokuapp.com/api/";
    private static RetrofitClient mInstance;
    private Retrofit retrofit;

    private RetrofitClient() {

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitClient getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }

    public LancamentoAPI getLancamentoApi() {
        return retrofit.create(LancamentoAPI.class);
    }

    public UsuarioAPI getUsuarioApi() {
        return retrofit.create(UsuarioAPI.class);
    }
}
