package com.ivan.bankapp.dagger.module;

import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SettingModule {

    public static final String BASE_URL = "Settings.ServerUrl";

    @Provides
    @Singleton
    @Named(BASE_URL)
    String providesServerUrl(Context context) {
        return "http://private-c60ade-guidebook1.apiary-mock.com";
    }
}
