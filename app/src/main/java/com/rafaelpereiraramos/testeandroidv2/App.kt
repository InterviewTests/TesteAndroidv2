package com.rafaelpereiraramos.testeandroidv2

import android.app.Activity
import android.app.Application
import com.rafaelpereiraramos.testeandroidv2.di.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by Rafael P. Ramos on 17/11/2018.
 */
class App : Application(), HasActivityInjector {

    @Inject lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

    override fun onCreate() {
        super.onCreate()

        AppInjector.inject(this)
    }

    companion object {
        val API = " https://bank-app-test.herokuapp.com/api/"
    }
}