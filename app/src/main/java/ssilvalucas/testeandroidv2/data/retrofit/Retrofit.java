package ssilvalucas.testeandroidv2.data.retrofit;

import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit {

    public static String API_BASE  = "https://bank-app-test.herokuapp.com/api/";

    public static retrofit2.Retrofit getInstance() {
        return new retrofit2.Retrofit.Builder()
                .baseUrl(API_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
