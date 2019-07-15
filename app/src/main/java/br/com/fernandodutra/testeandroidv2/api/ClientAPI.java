package br.com.fernandodutra.testeandroidv2.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 19/06/2019
 * Time: 11:29
 * TesteAndroidv2
 */
public class ClientAPI {

    public static final String URL_BASE = "https://bank-app-test.herokuapp.com";

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(URL_BASE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
