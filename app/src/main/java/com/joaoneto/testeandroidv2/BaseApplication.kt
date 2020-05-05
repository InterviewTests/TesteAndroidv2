package com.joaoneto.testeandroidv2

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import com.joaoneto.testeandroidv2.di.DaggerAppComponent

class BaseApplication : DaggerApplication(){

    override fun applicationInjector(): AndroidInjector<out DaggerApplication?>? {
        return DaggerAppComponent.builder().application(this).build()

    }

}