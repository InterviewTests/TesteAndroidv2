package br.com.rms

import android.app.Activity
import android.app.Application
import br.com.rms.bankapp.BuildConfig
import br.com.rms.bankapp.di.component.DaggerAppComponent
import com.facebook.stetho.Stetho
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class MyApplication : Application(), HasActivityInjector{

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()

        if(BuildConfig.DEBUG){
            Stetho.initializeWithDefaults(this)
        }

        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)

    }

}