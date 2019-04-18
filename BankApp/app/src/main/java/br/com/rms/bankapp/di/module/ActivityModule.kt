package br.com.rms.bankapp.di.module

import br.com.rms.bankapp.di.ActivityScope
import br.com.rms.bankapp.ui.home.HomeActivity
import br.com.rms.bankapp.ui.home.HomeFragmentProvider
import br.com.rms.bankapp.ui.login.LoginActivity
import br.com.rms.bankapp.ui.login.LoginFragmentProvider
import br.com.rms.bankapp.ui.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector()
    internal abstract fun contributeSplashActivity(): SplashActivity


    @ActivityScope
    @ContributesAndroidInjector(modules = [LoginFragmentProvider::class])
    internal abstract fun contributeLoginActivity(): LoginActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [HomeFragmentProvider::class])
    internal abstract fun contributeHomeActivity(): HomeActivity

}