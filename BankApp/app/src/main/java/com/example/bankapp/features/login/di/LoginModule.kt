package com.example.bankapp.features.login.di

import com.example.bankapp.features.login.presentation.LoginActivity
import com.example.base.annotation.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class LoginModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [LoginActivityModule::class])
    abstract fun contributesLoginActivity(): LoginActivity
}