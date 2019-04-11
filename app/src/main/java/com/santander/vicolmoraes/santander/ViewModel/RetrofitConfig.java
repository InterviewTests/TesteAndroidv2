package com.santander.vicolmoraes.santander.ViewModel;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitConfig {
    private final Retrofit retrofit;

    public RetrofitConfig() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("https://bank-app-test.herokuapp.com/api/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public TransacaoService getTransacoes() {
        return this.retrofit.create(TransacaoService.class);
    }

    public UsuarioService getUsuario() {
        return this.retrofit.create(UsuarioService.class);
    }
}
