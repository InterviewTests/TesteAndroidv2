package com.renanferrari.testeandroidv2.application.di.modules;

import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.renanferrari.testeandroidv2.domain.model.auth.AuthApi;
import com.renanferrari.testeandroidv2.domain.model.statements.StatementsRepository;
import com.renanferrari.testeandroidv2.domain.model.user.UserManager;
import com.renanferrari.testeandroidv2.infrastructure.BankApi;
import com.renanferrari.testeandroidv2.infrastructure.BankService;
import com.renanferrari.testeandroidv2.infrastructure.LocalUserManager;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module public class InfrastructureModule {

  private static final String BASE_URL = "https://bank-app-test.herokuapp.com/api/";

  @Provides @Singleton Gson provideGson() {
    return new GsonBuilder().create();
  }

  @Provides @Singleton OkHttpClient provideOkHttpClient() {
    return new OkHttpClient.Builder().build();
  }

  @Provides @Singleton Retrofit provideRetrofit(final Gson gson, final OkHttpClient okHttpClient) {
    return new Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .baseUrl(BASE_URL)
        .build();
  }

  @Provides @Singleton BankApi provideBankApi(final Retrofit retrofit) {
    return retrofit.create(BankApi.class);
  }

  @Provides @Singleton BankService provideBankService(final BankApi bankApi) {
    return new BankService(bankApi);
  }

  @Provides @Singleton AuthApi provideAuthApi(final BankService bankService) {
    return bankService;
  }

  @Provides @Singleton UserManager provideUserManager(final SharedPreferences sharedPreferences,
      final Gson gson) {
    return new LocalUserManager(sharedPreferences, gson);
  }

  @Provides @Singleton StatementsRepository provideStatementsRepository(
      final BankService bankService) {
    return bankService;
  }
}