package br.com.douglas.fukuhara.bank.network;

import br.com.douglas.fukuhara.bank.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitImpl implements RestClient {
    private static RetrofitImpl instance;

    private String BASE_URL = BuildConfig.BASE_URL;

    public static RetrofitImpl getInstance() {
        if (instance == null) {
            instance = new RetrofitImpl();
        }
        return instance;
    }

    @Override
    public RestApi getApi() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor);

            builder.client(client.build());
        }

        Retrofit retrofitClient = builder.build();

        return retrofitClient.create(RestApi.class);
    }

    @Override
    public void setBaseUrl(String url) {
        BASE_URL = url;
    }
}
