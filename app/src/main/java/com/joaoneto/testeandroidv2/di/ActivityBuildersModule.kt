package com.joaoneto.testeandroidv2.di

import com.joaoneto.testeandroidv2.di.login.LoginModule
import com.joaoneto.testeandroidv2.di.login.LoginViewModelsModule
import com.joaoneto.testeandroidv2.di.main.MainModule
import com.joaoneto.testeandroidv2.di.main.MainViewModelsModule
import com.joaoneto.testeandroidv2.loginscreen.view.activity.LoginActivity
import com.joaoneto.testeandroidv2.mainscreen.view.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
        modules = [
            LoginViewModelsModule::class,
            MainViewModelsModule::class,
            LoginModule::class,
            MainModule::class
        ]
    )
    abstract fun loginActivity(): LoginActivity

    abstract fun mainActivity(): MainActivity

}