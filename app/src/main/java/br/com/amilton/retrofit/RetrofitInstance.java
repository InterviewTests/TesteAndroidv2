package br.com.amilton.retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static Retrofit retrofit = null;

    public static synchronized WebService geWebService() {
        if (retrofit == null) {
            retrofit = new Retrofit
                    .Builder()
                    .client(new OkHttpClient
                            .Builder()
                            .connectTimeout(10, TimeUnit.SECONDS)
                            .build())
                    .baseUrl(WebService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(WebService.class);
    }
}
