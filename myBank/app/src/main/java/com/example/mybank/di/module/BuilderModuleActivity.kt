package com.example.mybank.di.module

import com.example.mybank.screens.accountDetail.AccountDetailActivity
import com.example.mybank.screens.login.LoginActivity
import com.example.mybank.screens.splash.SplashAtivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuilderModuleActivity {


    @ContributesAndroidInjector
    internal abstract fun contributesSplashActivity(): SplashAtivity

    @ContributesAndroidInjector
    internal abstract fun contributesLoginActivity(): LoginActivity

    @ContributesAndroidInjector
    internal abstract fun contributesAccountDetailActivity(): AccountDetailActivity

}
