package com.gft.testegft.di.module;

import com.gft.testegft.login.LoginActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector()
    abstract LoginActivity bindLoginActivity();
}