package com.example.ibm_test.di.component

import com.example.ibm_test.MainApplication
import com.example.ibm_test.clean_code.home.ui.HomeActivity
import com.example.ibm_test.clean_code.login.ui.LoginActivity
import com.example.ibm_test.di.module.*
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class, InteractorModule::class, PresenterModule::class, LocalStorageModule::class])

interface ApplicationComponent {
    fun inject(application: MainApplication)
    fun inject(loginActivity: LoginActivity)
    fun inject(homeActivity: HomeActivity)
}