package com.example.ibm_test.di.component

import com.example.ibm_test.MainApplication
import com.example.ibm_test.clean_code.login.ui.LoginActivity
import com.example.ibm_test.di.module.ApplicationModule
import com.example.ibm_test.di.module.InteractoModule
import com.example.ibm_test.di.module.NetworkModule
import com.example.ibm_test.di.module.PresenterModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class, InteractoModule::class, PresenterModule::class])

interface ApplicationComponent {
    fun inject(application: MainApplication)
    fun inject(loginActivity: LoginActivity)
}