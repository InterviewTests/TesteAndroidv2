package com.example.ibm_test.di.module

import android.content.Context
import com.example.ibm_test.clean_code.login.presenter.LoginPresenterInput
import com.example.ibm_test.clean_code.login.presenter.LoginPresenterOutput
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class PresenterModule{
    @Provides
    @Singleton
    fun provideLoginPresenterInput(context: Context): LoginPresenterInput
            = LoginPresenterOutput(context)
}