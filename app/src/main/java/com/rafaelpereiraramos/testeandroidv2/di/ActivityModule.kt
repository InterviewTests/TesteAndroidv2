package com.rafaelpereiraramos.testeandroidv2.di

import com.rafaelpereiraramos.testeandroidv2.view.login.LoginActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Rafael P. Ramos on 17/11/2018.
 */
@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun bindLoginActivity(): LoginActivity
}