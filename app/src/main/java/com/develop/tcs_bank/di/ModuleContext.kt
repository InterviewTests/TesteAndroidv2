package com.develop.tcs_bank.di

import android.content.Context
import com.develop.tcs_bank.infrastructure.repositories.disk.SPUtils
import com.develop.tcs_bank.presentation.main.TcsApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class ModuleContext {

    @Provides
    fun providesContext(): Context {
        return TcsApplication.instance.baseContext
    }

    @Provides
    @Singleton
    fun providesSPUtils(): SPUtils {
        return SPUtils()
    }
}