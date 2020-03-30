package com.example.bankapp.features.details.di

import com.example.bankapp.features.details.presentantion.DetailsActivity
import com.example.base.annotation.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DetailsModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = [DetailsActivityModule::class])
    abstract fun contributes(): DetailsActivity
}