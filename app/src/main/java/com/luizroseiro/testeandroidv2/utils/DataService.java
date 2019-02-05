package com.luizroseiro.testeandroidv2.utils;

import com.luizroseiro.testeandroidv2.loginScreen.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.luizroseiro.testeandroidv2.utils.Statics.API_URL;

public class DataService {

    private static ApiClient apiClient;

    static {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiClient = retrofit.create(ApiClient.class);
    }

    public static void loginUser(String user, String password, Callback<LoginResponse> callback) {
        Call<LoginResponse> call = apiClient.loginUser(user, password);
        call.enqueue(callback);
    }

}
