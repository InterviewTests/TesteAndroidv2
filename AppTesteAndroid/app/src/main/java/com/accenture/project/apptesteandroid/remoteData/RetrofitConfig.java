package com.accenture.project.apptesteandroid.remoteData;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Obtém a instância do objeto Retrofir
 * Configura o Retrofit:
 * Url base da API
 * Conversor dos dados obtidos da API para classes Java
 *
 */

public class RetrofitConfig {

    public static Retrofit getConfig() {
        return new Retrofit.Builder()
                .baseUrl("https://bank-app-test.herokuapp.com/api/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

    }
}
