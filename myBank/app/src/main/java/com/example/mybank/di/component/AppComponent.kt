package com.example.mybank.di.component

import android.app.Application
import com.example.mybank.MyBankApp
import com.example.mybank.di.module.AppModule
import com.example.mybank.di.module.BuilderModuleActivity
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, BuilderModuleActivity::class])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: MyBankApp)
}
