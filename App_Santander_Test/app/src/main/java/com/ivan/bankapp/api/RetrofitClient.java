package com.ivan.bankapp.api;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.ivan.bankapp.model.StatementList;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String AUTH = "";

    public static final String BASE_URL = "https://bank-app-test.herokuapp.com/api/";
    private static RetrofitClient mInstance;
    private Retrofit retrofit;

    private final BreedApiService breedApiService;

    private RetrofitClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .addInterceptor(interceptor)
                .build();

        breedApiService = new BreedApiService();


        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
    }

    public static synchronized RetrofitClient getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }

    public Api getApi() {
        return retrofit.create(Api.class);
    }

    public void getStatemtns(NetworkResponseListener<StatementList> listener) {
        breedApiService.getStatement(listener, getApi());
    }

}
