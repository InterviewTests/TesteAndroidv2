package br.com.kakobotasso.testeandroidv2.util;

import java.util.concurrent.TimeUnit;

import br.com.kakobotasso.testeandroidv2.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfiguration {
    private static final int DURATION_TIME = 10;
    private static final String BASE_URL = "https://bank-app-test.herokuapp.com/";

    public static Object getService(Class interfaceService) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(DURATION_TIME, TimeUnit.SECONDS);
        httpClient.writeTimeout(DURATION_TIME, TimeUnit.SECONDS);
        httpClient.readTimeout(DURATION_TIME, TimeUnit.SECONDS);
        httpClient = addLog(httpClient);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        return retrofit.create(interfaceService);
    }

    private static OkHttpClient.Builder addLog(OkHttpClient.Builder httpClient) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        HttpLoggingInterceptor.Level logLevel;

        if (BuildConfig.DEBUG) {
            logLevel = HttpLoggingInterceptor.Level.BODY;
        } else {
            logLevel = HttpLoggingInterceptor.Level.NONE;
        }

        loggingInterceptor.setLevel(logLevel);
        httpClient.addInterceptor(loggingInterceptor);
        return httpClient;
    }
}
