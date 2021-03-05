package br.com.silas.testeandroidv2

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import br.com.silas.testeandroidv2.di.dataModule
import br.com.silas.testeandroidv2.di.domainModule
import br.com.silas.testeandroidv2.di.presentaionModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(
                presentaionModule,
                domainModule,
                dataModule
            )
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}