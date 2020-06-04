package com.gft.testegft.di.module;

import com.gft.testegft.login.LoginActivity;
import com.gft.testegft.statements.StatementsActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector()
    abstract LoginActivity bindLoginActivity();

    @ContributesAndroidInjector()
    abstract StatementsActivity bindStatementsActivity();
}