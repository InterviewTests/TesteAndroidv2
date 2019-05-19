package com.danjdt.testeandroidv2.di.component


import com.danjdt.testeandroidv2.BankApplication
import com.danjdt.testeandroidv2.di.module.AppModule

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(app: BankApplication)

}