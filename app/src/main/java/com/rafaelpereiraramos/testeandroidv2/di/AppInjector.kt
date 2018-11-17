package com.rafaelpereiraramos.testeandroidv2.di

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.rafaelpereiraramos.testeandroidv2.App
import com.rafaelpereiraramos.testeandroidv2.core.DaggerAppComponent
import dagger.android.AndroidInjection

/**
 * Created by Rafael P. Ramos on 17/11/2018.
 */
abstract class AppInjector {
    companion object {
        fun inject(application: App) {
            DaggerAppComponent.builder().application(application).build().inject(application)

            application.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks{


                override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
                    AndroidInjection.inject(activity)
                }
                override fun onActivityPaused(activity: Activity?) {
                }

                override fun onActivityResumed(activity: Activity?) {
                }

                override fun onActivityStarted(activity: Activity?) {
                }

                override fun onActivityDestroyed(activity: Activity?) {
                }

                override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
                }

                override fun onActivityStopped(activity: Activity?) {
                }
            })
        }
    }
}