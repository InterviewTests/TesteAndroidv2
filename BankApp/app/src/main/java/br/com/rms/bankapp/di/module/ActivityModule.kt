package br.com.rms.bankapp.di.module

import br.com.rms.bankapp.ui.SplashActivity.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contributeSplashActivity(): SplashActivity
}