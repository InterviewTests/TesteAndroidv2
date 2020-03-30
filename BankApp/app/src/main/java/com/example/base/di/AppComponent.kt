package com.example.base.di

import android.app.Application
import com.example.bankapp.features.details.di.DetailsModule
import com.example.bankapp.features.login.di.LoginModule
import com.example.base.MyApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        LoginModule::class,
        DetailsModule::class,
        NetworkingModule::class,
        ViewModelModule::class
    ]
)

interface AppComponent : AndroidInjector<MyApplication> {

    fun inject(app: Application)

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun applicationBind(application: Application): Builder
    }
}