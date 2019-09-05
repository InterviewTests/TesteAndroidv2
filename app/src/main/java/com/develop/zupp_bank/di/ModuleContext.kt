package com.develop.zupp_bank.di

import android.content.Context
import com.develop.zupp_bank.infrastructure.repositories.disk.SPUtils
import com.develop.zupp_bank.presentation.main.ZupApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class ModuleContext {

    @Provides
    fun providesContext(): Context {
        return ZupApplication.instance.baseContext
    }

    @Provides
    @Singleton
    fun providesSPUtils(): SPUtils {
        return SPUtils()
    }
}