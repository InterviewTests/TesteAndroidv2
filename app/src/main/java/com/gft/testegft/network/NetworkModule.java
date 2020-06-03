package com.gft.testegft.network;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
abstract class NetworkModule {

    @Provides
    @Named("api_url")
    static String providesBaseUrl() {
        return "https://bank-app-test.herokuapp.com/api/";
    }
}