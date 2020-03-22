package com.example.ibm_test.di.component

import com.example.ibm_test.MainApplication
import com.example.ibm_test.di.module.ApplicationModule
import com.example.ibm_test.di.module.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class])

interface ApplicationComponent {
    fun inject(application: MainApplication)
}