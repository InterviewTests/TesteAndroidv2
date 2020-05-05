package com.paulokeller.bankapp

import android.app.Application
import com.paulokeller.bankapp.data.repositories.Repository
import com.paulokeller.bankapp.data.repositories.RepositoryImpl
import com.paulokeller.bankapp.data.services.Client
import com.paulokeller.bankapp.ui.login.LoginViewModelFactory
import com.paulokeller.bankapp.ui.statements.StatementsViewModelFactory
import okhttp3.OkHttpClient
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BankAppApplication : Application(), KodeinAware {
    private val baseURL = "https://bank-app-test.herokuapp.com"

    override val kodein = Kodein.lazy {
        bind<Repository>() with singleton { RepositoryImpl(context) }
        bind<Client>() with singleton {
            Retrofit.Builder()
                .client(OkHttpClient().newBuilder().build())
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(Client::class.java)
        }
        bind() from provider { LoginViewModelFactory(instance(), instance()) }
        bind() from provider { StatementsViewModelFactory(instance()) }
    }
}