package com.example.base

import android.app.Application
import com.example.base.di.AppComponent
import com.example.base.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MyApplication : Application(), HasAndroidInjector  {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Any>

    lateinit var appComponentCreator: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponentCreator = DaggerAppComponent.builder()
            .applicationBind(this)
            .build()

        appComponentCreator.inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = activityInjector

}