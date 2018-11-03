package com.ygorcesar.testeandroidv2.application

import android.app.Application
import android.support.v7.app.AppCompatDelegate
import com.ygorcesar.testeandroidv2.BuildConfig
import com.ygorcesar.testeandroidv2.application.di.ApplicationComponent
import com.ygorcesar.testeandroidv2.application.di.DaggerApplicationComponent
import com.ygorcesar.testeandroidv2.application.di.modules.ApplicationModule
import com.ygorcesar.testeandroidv2.base.di.BaseComponent
import timber.log.Timber

open class TesteAndroidV2Application : Application() {

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        setupTimber()

        val appComponent = createApplicationComponent()
        ApplicationComponent.INSTANCE = appComponent
        BaseComponent.INSTANCE = appComponent
    }

    open fun createApplicationComponent(): ApplicationComponent {
        return DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this@TesteAndroidV2Application))
            .build()
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}