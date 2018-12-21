package teste.claudio.com.testsantander;

import retrofit2.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.OkHttpClient;
import java.util.concurrent.TimeUnit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;


public class ServiceGenerator {
    //URL base do endpoint. Deve sempre terminar com /
    public static final String API_BASE_URL_LOGIN = "https://bank-app-test.herokuapp.com/api/";

    public static <S> S createService(Class<S> serviceClass) {

        //Instancia do interceptador das requisições
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .readTimeout(15, TimeUnit.SECONDS);

        httpClient.addInterceptor(loggingInterceptor);

        //Instância do retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL_LOGIN)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();


        return retrofit.create(serviceClass);
    }
}

