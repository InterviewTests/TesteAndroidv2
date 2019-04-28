package br.com.alex.bankappchallenge

import android.app.Application
import br.com.alex.bankappchallenge.di.repositoryModule
import br.com.alex.bankappchallenge.di.networkModule
import br.com.alex.bankappchallenge.di.viewModelModule
import br.com.alex.bankappchallenge.di.androidModule
import br.com.alex.bankappchallenge.di.interactorModule
import br.com.alex.bankappchallenge.di.reducerModule
import br.com.alex.bankappchallenge.di.PROPERTY_BASE_URL
import com.orhanobut.hawk.Hawk
import org.koin.core.context.startKoin

class BankAppChallengeApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
        setupHawk()
    }

    private fun setupKoin() {
        startKoin {
            modules(listOf(
                networkModule,
                viewModelModule,
                androidModule,
                interactorModule,
                reducerModule,
                repositoryModule
            ))

            properties(mapOf(
                PROPERTY_BASE_URL to BuildConfig.API_BASE
            ))
        }
    }

    private fun setupHawk() = Hawk.init(this).build()
}