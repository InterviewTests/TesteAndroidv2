package com.santander.data.di

import android.content.Context
import android.content.SharedPreferences
import com.santander.data.BuildConfig
import com.santander.data.repository.AccountRepositoryImpl
import com.santander.data.repository.LoginRepositoryImpl
import com.santander.data.repository.StatementRepositoryImpl
import com.santander.data.source.local.preferences.IPreferencesManager
import com.santander.data.source.local.preferences.PreferencesManager
import com.santander.data.source.remote.API
import com.santander.data.source.remote.NetworkProvider
import com.santander.data.util.network.ConnectivityManager
import com.santander.data.util.security.crypto.CryptoManager
import com.santander.domain.repository.IAccountRepository
import com.santander.domain.repository.ILoginRepository
import com.santander.domain.repository.IStatementRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

object RepositoryModule {

    val module = module {

        single<ConnectivityManager> {
            ConnectivityManager(context = androidContext())
        }

        single<CryptoManager> {
            CryptoManager.Builder("key_santander", androidContext()).build()
        }

        single<SharedPreferences> {
            this.androidContext().getSharedPreferences("pref_santander", Context.MODE_PRIVATE)
        }

        single<IPreferencesManager> {
            PreferencesManager(preferences = get(), cryptographyManager = get())
        }

        single<API> {
            NetworkProvider(connectivityManager = get())
                .createService(serviceClass = API::class.java, baseUrl = BuildConfig.BASE_URL)
        }

        single<ILoginRepository> {
            LoginRepositoryImpl(api = get(), pref = get())
        }

        single<IAccountRepository> {
            AccountRepositoryImpl(pref = get())
        }

        single<IStatementRepository> {
            StatementRepositoryImpl(api = get())
        }
    }
}