package com.example.desafiosantander

import android.app.Application
import com.example.desafiosantander.di.BASE_URL
import com.example.desafiosantander.di.networkModule
import com.example.desafiosantander.di.repositoryModule
import com.example.desafiosantander.di.viewModelModule
import com.orhanobut.hawk.Hawk
import org.koin.core.context.startKoin

class BankApplicationTest : Application() {

    override fun onCreate() {
        super.onCreate()

        Hawk.init(this).build()

        startKoin {
            modules(getModule())
            properties(mapOf(BASE_URL to "http://localhost:8080/"))
        }
    }

    private fun getModule() = listOf(networkModule, repositoryModule, viewModelModule)
}