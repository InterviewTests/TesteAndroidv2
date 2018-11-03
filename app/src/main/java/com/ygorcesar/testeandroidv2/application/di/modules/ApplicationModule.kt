package com.ygorcesar.testeandroidv2.application.di.modules

import android.app.Application
import android.content.Context
import com.ygorcesar.testeandroidv2.application.di.scopes.ApplicationScope
import com.ygorcesar.testeandroidv2.home.data.HomeRepository
import com.ygorcesar.testeandroidv2.login.data.LoginRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Context {
        return application
    }

    @Provides
    @ApplicationScope
    fun provideLoginRepository(dataSource: LoginRepository.Remote): LoginRepository = dataSource

    @Provides
    @ApplicationScope
    fun provideHomeRepository(dataSource: HomeRepository.Remote): HomeRepository = dataSource

}