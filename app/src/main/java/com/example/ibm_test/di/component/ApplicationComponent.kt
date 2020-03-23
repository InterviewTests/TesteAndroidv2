package com.example.ibm_test.di.component

import com.example.ibm_test.MainApplication
import com.example.ibm_test.clean_code.login.ui.LoginActivity
import com.example.ibm_test.di.module.*
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class, InteractoModule::class, PresenterModule::class, LocalStorageModule::class])

interface ApplicationComponent {
    fun inject(application: MainApplication)
    fun inject(loginActivity: LoginActivity)
}